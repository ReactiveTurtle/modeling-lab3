import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { NodeListComponent } from './node-list/node-list.component';

const routes: Routes = [
  {
    path: 'manager/:nodeId',
    component: NodeListComponent,
    children: []
  },
  {
    path: 'manager',
    component: NodeListComponent,
    children: []
  },
  {
    path: '',
    redirectTo: '/manager',
    pathMatch: 'full'
  },
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
