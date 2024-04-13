import { Component, OnInit } from '@angular/core';
import { Task } from '../../model/task';
import { TaskService } from '../../service/task.service';
import { HttpErrorResponse } from '@angular/common/http';

@Component({
  selector: 'app-task-list',
  templateUrl: './task-list.component.html',
  styleUrl: './task-list.component.css'
})
export class TaskListComponent implements OnInit {

  public tasks: Task[];

  constructor(private taskService: TaskService) {
  }

  ngOnInit() {
      this.taskService.findAll().subscribe(
        (response: Task[]) => {
          this.tasks = response;
        },
        (error: HttpErrorResponse) => {
          console.log(error.message);
        }
      );
  }
}
