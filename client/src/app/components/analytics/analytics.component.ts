import { Component, OnInit } from '@angular/core';
import { ChartDataSets, ChartOptions, ChartType } from 'chart.js';
import { Color, Label, SingleDataSet } from 'ng2-charts';


@Component({
  selector: 'app-analytics',
  templateUrl: './analytics.component.html',
  styleUrls: ['./analytics.component.scss']
})
export class AnalyticsComponent implements OnInit {
  // ! All charts should be extracted to separate components
  // Line chart
  lineChartData: ChartDataSets[] = [
    { data: [65, 59, 80, 81, 56, 55, 72, 90], label: 'Выручка в т.р.' },
  ];
  lineChartLabels: Label[] = ['Январь', 'Февраль', 'Март', 'Апрель', 'Мая', 'Июнь', 'Июль', 'Август'];
  lineChartOptions: ChartOptions = {
    responsive: true,
  };
  lineChartColors: Color[] = [
    {
      borderColor: 'black',
      backgroundColor: 'rgba(255,0,0,0.3)',
    },
  ];
  lineChartLegend = true;
  lineChartType = 'line';
  lineChartPlugins = [];

  // bar chart
  barChartOptions: ChartOptions = {
    responsive: true,
  };
  barChartLabels: Label[] = ['2012', '2013', '2014', '2015', '2016', '2017', '2018'];
  barChartType: ChartType = 'bar';
  barChartLegend = true;
  barChartPlugins = [];

  barChartData: ChartDataSets[] = [
    { data: [6, 2, 3, 5, 9, 3, 13], label: 'Расходы млн.р.' },
    { data: [7, 3, 2, 4, 9, 5, 18], label: 'Доходы млн.р.' }
  ];

  // PolarArea
  polarAreaChartLabels: Label[] = ['Продачи через магазин', 'Оннлайн продажи', 'Продажи через агентов', 'Продажи через соц. сети', 'Оптовые продажи'];
  polarAreaChartData: SingleDataSet = [300, 500, 100, 40, 120];
  polarAreaLegend = true;

  polarAreaChartType: ChartType = 'polarArea';

  constructor() { }

  ngOnInit() {
  }

}
