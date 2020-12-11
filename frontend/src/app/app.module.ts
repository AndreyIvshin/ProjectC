import {BrowserModule} from '@angular/platform-browser';
import {NgModule} from '@angular/core';

import {AppRoutingModule} from './app-routing.module';
import {AppComponent} from './app.component';
import {BrowserAnimationsModule} from '@angular/platform-browser/animations';
import {HeaderComponent} from './component/common/header/header.component';
import {FooterComponent} from './component/common/footer/footer.component';
import {ContentComponent} from './component/common/content/content.component';
import {WelcomeComponent} from './component/common/welcome/welcome.component';
import {MatToolbarModule} from '@angular/material/toolbar';
import {MatButtonModule} from '@angular/material/button';
import {SignInComponent} from './component/common/sign-in/sign-in.component';
import {NotFoundComponent} from './component/common/not-found/not-found.component';
import {MatCardModule} from '@angular/material/card';
import {MatInputModule} from '@angular/material/input';
import {HTTP_INTERCEPTORS, HttpClientModule} from '@angular/common/http';
import {FormsModule, ReactiveFormsModule} from '@angular/forms';
import {MatFormFieldModule} from '@angular/material/form-field';
import {MatIconModule} from '@angular/material/icon';
import {MatMenuModule} from '@angular/material/menu';
import {InterceptorService} from './service/interceptor.service';
import {AuthGuard} from './service/auth.guard';
import {ItemListComponent} from './component/item/item-list/item-list.component';
import {MatListModule} from '@angular/material/list';
import {MatProgressSpinnerModule} from '@angular/material/progress-spinner';
import {ErrorComponent} from './component/common/error/error.component';
import {ItemWriteComponent} from './component/item/item-write/item-write.component';
import {ItemAddComponent} from './component/item/item-add/item-add.component';
import {UnitListComponent} from './component/unit/unit-list/unit-list.component';
import {UnitAddComponent} from './component/unit/unit-add/unit-add.component';
import {UnitWriteComponent} from './component/unit/unit-write/unit-write.component';
import {EntityWriteComponent} from './component/entity/entity-write/entity-write.component';
import {EntityListComponent} from './component/entity/entity-list/entity-list.component';
import {EntityAddComponent} from './component/entity/entity-add/entity-add.component';
import {DealAddComponent} from './component/deal/deal-add/deal-add.component';
import {DealWriteComponent} from './component/deal/deal-write/deal-write.component';
import {DealListComponent} from './component/deal/deal-list/deal-list.component';
import {MatDatepickerModule} from '@angular/material/datepicker';
import {MatNativeDateModule} from '@angular/material/core';
import {MatSelectModule} from '@angular/material/select';
import { ReportComponent } from './component/report/report.component';
import {MatExpansionModule} from '@angular/material/expansion';

@NgModule({
  declarations: [
    AppComponent,
    HeaderComponent,
    FooterComponent,
    ContentComponent,
    WelcomeComponent,
    NotFoundComponent,
    SignInComponent,
    ErrorComponent,
    ItemListComponent,
    ItemWriteComponent,
    ItemAddComponent,
    UnitListComponent,
    UnitWriteComponent,
    UnitAddComponent,
    EntityListComponent,
    EntityWriteComponent,
    EntityAddComponent,
    DealListComponent,
    DealWriteComponent,
    DealAddComponent,
    ReportComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    BrowserAnimationsModule,
    HttpClientModule,
    FormsModule,
    ReactiveFormsModule,
    MatToolbarModule,
    MatButtonModule,
    MatMenuModule,
    MatIconModule,
    MatCardModule,
    MatFormFieldModule,
    MatInputModule,
    MatListModule,
    MatProgressSpinnerModule,
    MatDatepickerModule,
    MatNativeDateModule,
    MatSelectModule,
    MatExpansionModule
  ],
  providers: [
    AuthGuard,
    {
      provide: HTTP_INTERCEPTORS,
      useClass: InterceptorService,
      multi: true
    }
  ],
  bootstrap: [AppComponent]
})
export class AppModule {
}
