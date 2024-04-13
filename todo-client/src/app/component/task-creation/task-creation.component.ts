import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { TaskService } from '../../service/task.service';
import { CatalogService } from '../../service/catalog.service';
import { Task } from '../../model/task';
import { Category } from '../../model/category';
import { HttpErrorResponse } from '@angular/common/http';

@Component({
  selector: 'task-creation',
  templateUrl: './task-creation.component.html',
  styleUrl: './task-creation.component.css'
})
export class TaskCreationComponent implements OnInit {

  categories: Category[];
  task: Task;

  ngOnInit() {
      this.catalogService.getCategories().subscribe(
        (response: Category[]) => {
          this.categories = response;
        },
        (error: HttpErrorResponse) => {
          console.log(error.message);
        }
      );
  }

  

  constructor(
    private route: ActivatedRoute, 
      private router: Router, 
        private taskService: TaskService,
          private catalogService: CatalogService) {
    this.task = new Task();
  }

  onSubmit() {
    this.taskService.save(this.task).subscribe(
      (response: Task) => {
        this.gotoTasksList();
      },
      (error: HttpErrorResponse) => {
        console.log(error.message);
      }
    );
  }

  gotoTasksList() {
    this.router.navigate(['/tasks']);
  }
}