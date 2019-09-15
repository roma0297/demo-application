import { ComponentPortal } from '@angular/cdk/portal';
export type TStep = {
  id: number,
  title: string,
  finished: boolean,
  active: boolean,
  tooltipText: string,
  description: string
};

export type TObject = {
  [k: string]: any
};

export type TTab = {
  id: number;
  label: string,
  content?: ComponentPortal<any>
}

export interface IMarketItem {
  id: number;
  name: string;
  subtitle: string;
  url: string;
  description: string;
  enabled: boolean;
  isShown: boolean;
}

export type TUserLoginData = {
  password: string,
  username: string
}