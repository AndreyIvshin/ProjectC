import {Component, OnInit} from '@angular/core';
import {Router} from '@angular/router';
import {DealService} from '../../../service/deal.service';
import {DealLite} from '../../../model/deal/deal-lite';

@Component({
  selector: 'app-deal-list',
  templateUrl: './deal-list.component.html',
  styleUrls: ['./deal-list.component.css']
})
export class DealListComponent implements OnInit {

  constructor(private service: DealService, private router: Router) { }

  deals: DealLite[] = [];

  ngOnInit(): void {
    this.service.getDealList().subscribe(value => this.deals = value.sort((a, b) => {
      const d1 = a.date === undefined ? new Date() : a.date;
      const d2 = b.date === undefined ? new Date() : b.date;
      return d1 < d2 ? 1 : -1;
    }));
  }

  remove(id: number): void {
    this.service.deleteDeal(id).subscribe(value => this.router.navigate(['deal-list']));
  }

}
