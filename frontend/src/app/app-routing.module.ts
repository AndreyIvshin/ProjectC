import {NgModule} from '@angular/core';
import {RouterModule, Routes} from '@angular/router';
import {WelcomeComponent} from './component/common/welcome/welcome.component';
import {SignInComponent} from './component/common/sign-in/sign-in.component';
import {NotFoundComponent} from './component/common/not-found/not-found.component';
import {AuthGuard} from './service/auth.guard';
import {ItemListComponent} from './component/item/item-list/item-list.component';
import {ErrorComponent} from './component/common/error/error.component';
import {ItemWriteComponent} from './component/item/item-write/item-write.component';
import {ItemAddComponent} from './component/item/item-add/item-add.component';
import {UnitAddComponent} from './component/unit/unit-add/unit-add.component';
import {UnitListComponent} from './component/unit/unit-list/unit-list.component';
import {UnitWriteComponent} from './component/unit/unit-write/unit-write.component';
import {EntityWriteComponent} from './component/entity/entity-write/entity-write.component';
import {EntityListComponent} from './component/entity/entity-list/entity-list.component';
import {EntityAddComponent} from './component/entity/entity-add/entity-add.component';
import {DealListComponent} from './component/deal/deal-list/deal-list.component';
import {DealWriteComponent} from './component/deal/deal-write/deal-write.component';
import {DealAddComponent} from './component/deal/deal-add/deal-add.component';
import {ReportComponent} from './component/report/report.component';

const routes: Routes = [
  {path: '', component: WelcomeComponent},
  {path: 'sign-in', component: SignInComponent},
  {path: 'item-add', component: ItemAddComponent, canActivate: [AuthGuard]},
  {path: 'item-list', component: ItemListComponent, canActivate: [AuthGuard]},
  {path: 'item-write/:id', component: ItemWriteComponent, canActivate: [AuthGuard]},
  {path: 'unit-add', component: UnitAddComponent, canActivate: [AuthGuard]},
  {path: 'unit-list', component: UnitListComponent, canActivate: [AuthGuard]},
  {path: 'unit-write/:id', component: UnitWriteComponent, canActivate: [AuthGuard]},
  {path: 'entity-add', component: EntityAddComponent, canActivate: [AuthGuard]},
  {path: 'entity-list', component: EntityListComponent, canActivate: [AuthGuard]},
  {path: 'entity-write/:id', component: EntityWriteComponent, canActivate: [AuthGuard]},
  {path: 'deal-add', component: DealAddComponent, canActivate: [AuthGuard]},
  {path: 'deal-list', component: DealListComponent, canActivate: [AuthGuard]},
  {path: 'deal-write/:id', component: DealWriteComponent, canActivate: [AuthGuard]},
  {path: 'report', component: ReportComponent, canActivate: [AuthGuard]},
  {path: 'error', component: ErrorComponent},
  {path: '**', component: NotFoundComponent}
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule {
}
