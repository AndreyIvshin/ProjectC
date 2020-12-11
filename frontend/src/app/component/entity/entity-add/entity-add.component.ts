import {Component, OnInit} from '@angular/core';
import {Router} from '@angular/router';
import {EntityToSave} from '../../../model/entity/entity-to-save';
import {EntityService} from '../../../service/entity.service';

@Component({
  selector: 'app-entity-add',
  templateUrl: './entity-add.component.html',
  styleUrls: ['./entity-add.component.css']
})
export class EntityAddComponent implements OnInit {

  constructor(private service: EntityService, private router: Router) { }

  entity = new EntityToSave();

  ngOnInit(): void {
  }

  add(): void {
    this.service.postEntity(this.entity).subscribe(value => this.router.navigate(['entity-list']));
  }

}
