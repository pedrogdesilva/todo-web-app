import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { HttpClientModule } from '@angular/common/http';
import { AppComponent } from './app.component';
import { TaskListComponent } from './component/task-list/task-list.component';
import { TaskService } from './service/task.service';
import { CatalogService } from './service/catalog.service';
import { AppRoutingModule } from './app-routing.module';
import { TaskCreationComponent } from './component/task-creation/task-creation.component';

@NgModule({
  declarations: [
    AppComponent,
    TaskListComponent,
    TaskCreationComponent
  ],
  imports: [
    BrowserModule,
    HttpClientModule,
    FormsModule,
    AppRoutingModule
  ],
  providers: [TaskService, CatalogService],
  bootstrap: [AppComponent]
})
export class AppModule { }