import { HttpClient } from '@angular/common/http';
import { Injectable, Inject } from '@angular/core';

import { TUserLoginData } from './../../common/types/market-place.d';
import { BASE_URL } from '../configs/tokens';

@Injectable({
  providedIn: 'root'
})
export class LoginService {

  constructor(@Inject(BASE_URL) private baseUrl: string, private http: HttpClient) { }

  authenticateUser(body: TUserLoginData) {
    return this.http.post(`${this.baseUrl}/api/login`, body);
  }
}
