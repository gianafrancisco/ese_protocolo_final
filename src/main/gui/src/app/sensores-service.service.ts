import { Injectable } from '@angular/core';
import { Headers, Http } from '@angular/http';

import 'rxjs/add/operator/toPromise';

import { Sensor } from './sensor';

@Injectable()
export class SensoresService {

  private url = '/values';  // URL to web api

  constructor(private http: Http) { }

  getValues(): Promise<Sensor[]> {
    return this.http.get(this.url)
               .toPromise()
               .then(response => response.json() as Sensor[])
               //.then(response => console.log(response.json()))
               .catch(this.handleError);
  }
  getValuesLocal(): Sensor[] {
  	let heroes = [
      {timestamp: "2016/10/22 10:00:00", value: 100, img: "/assets/sensor.png"},
      {timestamp: "2016/10/22 10:01:00", value: 101, img: "/assets/sensor.png"},
      {timestamp: "2016/10/22 10:02:00", value: 102, img: "/assets/sensor.png"},
      {timestamp: "2016/10/22 10:03:00", value: 103, img: "/assets/sensor.png"},
      {timestamp: "2016/10/22 10:04:00", value: 104, img: "/assets/sensor.png"}
    ];
    return heroes as Sensor[];
  }
  private handleError(error: any): Promise<any> {
  	console.error('An error occurred', error); // for demo purposes only
  	return Promise.reject(error.message || error);
  }

}
