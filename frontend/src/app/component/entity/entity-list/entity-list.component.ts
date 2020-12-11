import {Component, OnInit} from '@angular/core';
import {Router} from '@angular/router';
import {EntityService} from '../../../service/entity.service';
import {EntityLite} from '../../../model/entity/entity-lite';

@Component({
  selector: 'app-entity-list',
  templateUrl: './entity-list.component.html',
  styleUrls: ['./entity-list.component.css']
})
export class EntityListComponent implements OnInit {

  constructor(private service: EntityService, private router: Router) { }

  entities: EntityLite[] = [];

  ngOnInit(): void {
    this.service.getEntityList().subscribe(value => this.entities = value);
  }

  remove(id: number): void {
    this.service.deleteEntity(id).subscribe(value => this.router.navigate(['entity-list']));
  }

}
