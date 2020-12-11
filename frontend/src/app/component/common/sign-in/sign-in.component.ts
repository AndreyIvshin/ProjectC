import {Component, OnInit} from '@angular/core';
import {UserToAuth} from '../../../model/user-to-auth';
import {SecurityService} from '../../../service/security.service';
import {HttpErrorResponse} from '@angular/common/http';
import {Router} from '@angular/router';

@Component({
  selector: 'app-sign-in',
  templateUrl: './sign-in.component.html',
  styleUrls: ['./sign-in.component.css']
})
export class SignInComponent implements OnInit {

  constructor(private security: SecurityService, private router: Router) {
  }

  public user = new UserToAuth();
  public notFound = false;
  public forbidden = false;

  ngOnInit(): void {
  }

  signIn(): void {
    this.security.auth(this.user).subscribe(value => {
      this.security.login(value);
      this.router.navigate(['/']);
    }, error => {
      const resp = new HttpErrorResponse(error);
      this.notFound = resp.status === 404;
      this.forbidden = resp.status === 403;
    });
  }
}
