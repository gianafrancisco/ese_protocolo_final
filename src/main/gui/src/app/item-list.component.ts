import { Component } from '@angular/core';
import { Sensor } from './sensor';
import { SensoresService } from './sensores-service.service';

@Component({
  selector: 'app-item-list',
  templateUrl: './item-list.component.html',
  styleUrls: ['./item-list.component.css']
})
export class ItemListComponent {
   sensores: Array<Sensor> = [];

   constructor(private service: SensoresService){
		this.service.getValues().then(s => this.sensores = s);
   }

}
