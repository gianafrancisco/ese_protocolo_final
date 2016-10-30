export class Sensor {
  timestamp: string;
  value: number;
  img: string;
  id: number;

  constructor(timestamp: string, value: number, img: string, id: number){
    this.timestamp = timestamp;
    this.value = value;
    this.img = img;
    this.id = id;
  }
}
