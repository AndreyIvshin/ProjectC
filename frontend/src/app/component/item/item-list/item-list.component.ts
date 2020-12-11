import {Component, OnInit} from '@angular/core';
import {ItemService} from '../../../service/item.service';
import {Router} from '@angular/router';
import {ItemLite} from '../../../model/item/item-lite';

@Component({
  selector: 'app-item-list',
  templateUrl: './item-list.component.html',
  styleUrls: ['./item-list.component.css']
})
export class ItemListComponent implements OnInit {

  constructor(private service: ItemService, private router: Router) { }

  items: ItemLite[] = [];

  ngOnInit(): void {
    this.service.getItemList().subscribe(value => this.items = value);
  }

  remove(id: number): void {
    this.service.deleteItem(id).subscribe(value => this.router.navigate(['item-list']));
  }

}
