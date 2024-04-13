import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Task } from '../model/task';

@Injectable({
  providedIn: 'root'
 })
export class TaskService {

  private tasksUrl: string;

  constructor(private http: HttpClient) {
    this.tasksUrl = 'http://localhost:8080/rest/task';
  }

  public findAll(): Observable<Task[]> {
    return this.http.get<Task[]>(this.tasksUrl + '/all');
  }

  public save(task: Task) : Observable<Task>{
    return this.http.put<Task>(this.tasksUrl + '/create', task);
  }

  public update(task: Task) : Observable<Task>{
    return this.http.post<Task>(this.tasksUrl + '/update', task);
  }

}
