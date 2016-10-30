#include <QueueList.h>
#include <ESP8266WiFi.h>
#include <WiFiClient.h>


#define SERIAL 0
#define WAIT_ACK 1
#define COMPARE 2
#define RESET 3
#define SEND 4
#define POP_QUEUE 5
#define SHOW_QUEUE 6
#define NETWORK_ISSUE 0
#define NETWORK_OK 1
#define CHECK_INTERVAL 10000

const char* ssid = "Pi3-AP";
const char* password = "raspberry";
//const char* ssid = "WLANIUA_FI";
//const char* password = "";
const char* p = "punto y coma";
const char* queue = "queue";
const char* rq = "\nQueue list values\n";

unsigned char sensorId = 0x02;

// Remote site information
//const char http_site[] = "192.168.16.220";
const char http_site[] = "192.168.101.30";
const int http_port = 8080;
char value[32];
char valueRx[32];
int i = 0;
int irx = 0;
char incomingByte;
int estado = SERIAL;
String svalue = "";
String svaluerx = "";
QueueList <String> queueList;
int network_status;
long network_check = 0;

WiFiClient client;


void setup(void) {
  Serial.begin(115200);
  WiFi.begin(ssid, password);
  Serial.println("");

  while (WiFi.status() != WL_CONNECTED) {
    delay(500);
    Serial.print(".");
  }
  Serial.println("");
  Serial.print("Conectedo a ");
  Serial.println(ssid);
  Serial.print("Dirección IP: ");
  Serial.println(WiFi.localIP());

  IPAddress sensor_ip = WiFi.localIP();
   
}
void loop(void) {
  task_read_serial();
  if(estado == SEND){
    sendRequest(value, sensorId);
  }
  if(estado == WAIT_ACK)
    task_read_client(); 
  if(estado == COMPARE){
    Serial.print("\nRECV : ");
    Serial.println(valueRx);
    //Serial.print("Queue Value: ");
    svalue = queueList.peek();
    //Serial.print(svalue);
    if(svalue.equals(valueRx)){
      estado = POP_QUEUE;
    }else{
      irx = 0;
      valueRx[i] = 0;
      estado = RESET;
    }
  }
  if(estado == POP_QUEUE){
    String s = queueList.pop();
    Serial.println("\nDEQUEUE OK: " + s);
    estado = RESET;
  }
  if(estado == SHOW_QUEUE){
    Serial.println("\nCMD OK: queue");
    Serial.println(rq);
    Serial.println("---------------------------------------");
    QueueList <String> tmp;
    int qi = 0;
    while(!queueList.isEmpty()){
      qi++;
      Serial.print(qi);
      Serial.print(": ");
      String s = queueList.pop();
      Serial.println(s);
      tmp.push(s);
    }
    while(!tmp.isEmpty()){
      queueList.push(tmp.pop());
    }
    Serial.println("---------------------------------------");
    Serial.print("Count: ");
    Serial.println(queueList.count());
    Serial.println("---------------------------------------");

    estado = RESET;
  }
  if(estado == RESET){
    i = 0;
    value[i] = 0;
    irx = 0;
    valueRx[i] = 0;
    estado = SERIAL;
  }
  if(network_status == NETWORK_ISSUE){
    if(int(millis()-network_check) > CHECK_INTERVAL){
      network_check = millis();
      network_status = NETWORK_OK;
    }
  }else{
    network_check = millis();
  }
  if(estado == SERIAL && !queueList.isEmpty() && network_status == NETWORK_OK ){
      estado = SEND;
  }
  //delay(100);
}

bool sendRequest(char *value, unsigned char sensorId) {
  svalue = queueList.peek();
  client.stop();
  // Attempt to make a connection to the remote server
  if ( !client.connect(http_site, http_port) ) {
    Serial.println("NETWORK STATUS: connection lost.");
    estado = RESET;
    network_status = NETWORK_ISSUE;
    return false;
  }
  network_status = NETWORK_OK;
  
  // Make an HTTP GET request
  Serial.print("\nSEND CMD: ");
  client.print("GET /values");
  Serial.print("GET /values");
  client.print("?sensorId=");
  Serial.print("?sensorId=");
  client.print(sensorId);
  Serial.print(sensorId);
  client.print("&val=");
  Serial.print("&val=");
  client.print(svalue);
  Serial.print(svalue);
  client.println();
  Serial.println();
  estado = WAIT_ACK;
  return true;
}

void task_read_client(){
  if(client.connected()){
    if ( client.available() ) {
      char c = client.read();
      valueRx[irx++] = c;
      valueRx[irx] = 0x0;
      if(irx > 31) irx = 0;
      //Serial.print(c);
    }  
  }else{
    estado = COMPARE;
  }
}

void task_read_serial(){
  if (Serial.available() > 0) {
    incomingByte = Serial.read();
    value[i++] = incomingByte;
    value[i] = 0x0;
    if(value[i - 1] == ';'){
      value[i - 1] = 0x0;
      if(strcmp(value, queue) == 0){
        estado = SHOW_QUEUE;
      }else{
        svalue = value;
        if(svalue.toInt() != 0){
          queueList.push(svalue);
          Serial.println("QUEUE OK: " + svalue);
          estado = SEND;
        }else{
          Serial.println("CMD NOT FOUND: " + svalue);
          estado = RESET;
        }
      }
    }
  }
}



