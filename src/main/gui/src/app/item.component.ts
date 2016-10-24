import { Component, Input } from '@angular/core';
import { Sensor } from './sensor';

@Component({
  selector: 'app-item',
  templateUrl: './item.component.html',
  styleUrls: ['./item.component.css']
})
export class ItemComponent {
    @Input() sensor: Sensor;
}
