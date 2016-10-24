import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { HttpModule } from '@angular/http';

//import { InMemoryWebApiModule } from 'angular-in-memory-web-api/in-memory-web-api.module';
//import { InMemoryDataService }  from './in-memory-data.service';

import { AppComponent } from './app.component';
import { ItemListComponent } from './item-list.component';
import { ItemComponent } from './item.component';
import { HeadComponent } from './head.component';
import { SensoresService } from './sensores-service.service';

@NgModule({
  declarations: [
    AppComponent,
    ItemListComponent,
    ItemComponent,
    HeadComponent
  ],
  imports: [
    BrowserModule,
    FormsModule,
    HttpModule,
    //InMemoryWebApiModule.forRoot(InMemoryDataService)
  ],
  providers: [SensoresService],
  bootstrap: [AppComponent]
})
export class AppModule { }
