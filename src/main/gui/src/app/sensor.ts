export class Sensor {
  timestamp: string;
  value: number;
  img: string;

  constructor(timestamp: string, value: number, img: string){
    this.timestamp = timestamp;
    this.value = value;
    this.img = img;
  }
}
