import { Component, Input, OnInit, ViewChild } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { MatPaginator } from '@angular/material/paginator';
import { MatSort } from '@angular/material/sort';
import { MatTableDataSource } from '@angular/material/table';


type ColumnId = string | '_edit' | '_delete';

interface IModifier {
  name: string;
  func: (value) => {}
}

@Component({
  selector: 'app-table',
  templateUrl: './table.component.html',
  styleUrls: ['./table.component.scss']
})
export class TableComponent implements OnInit {
  @Input() label: string;
  @Input() buttons: [];
  @Input() data: any[];
  @Input() api: string;
  @Input() columnSizes: number[];
  @Input() columnIds: ColumnId[];
  @Input() columnNames: string[];
  @Input() columnModifiers: IModifier[];
  @Input() editPopupContentComponent: any;

  resultsLength = 0;
  isLoadingResults = true;
  isRateLimitReached = false;
  dataSource = new MatTableDataSource(this.data);

  @ViewChild(MatPaginator, { static: false}) paginator: MatPaginator;
  @ViewChild(MatSort, { static: false }) sort: MatSort;

  constructor() {}

  ngOnInit() {
    this.resultsLength = this.data.length;
  }

  getCellValue(value: any) {
    return value === true ? 'yes' : value === false ? 'no' : value;
  }

  cellModifier(row: any, columnName: string) {
    const modifier = this.columnModifiers && this.columnModifiers.find(element => element.name === columnName);

    let value = row[columnName];
    if (modifier) {
      value = modifier.func(row[columnName]);
    }

    return value;
  }
}
