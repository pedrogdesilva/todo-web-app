import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { TaskListComponent } from './component/task-list/task-list.component';
import { TaskCreationComponent } from './component/task-creation/task-creation.component'

const routes: Routes = [
  { path: 'tasks', component: TaskListComponent },
  { path: 'tasks/create', component: TaskCreationComponent },
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
