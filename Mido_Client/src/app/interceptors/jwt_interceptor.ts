import {HttpEvent, HttpHandler, HttpInterceptor, HttpRequest} from "@angular/common/http";
import {Injectable} from "@angular/core";
import {CookieService} from "ngx-cookie-service";
import {Observable} from "rxjs";

@Injectable()
export class JwtInterceptor implements HttpInterceptor {

  constructor(private cookieService: CookieService) {}

  intercept(req: HttpRequest<any>, next: HttpHandler): Observable<HttpEvent<any>> {
    const fullToken = this.cookieService.get('access_token');

    if (!!fullToken) {
      req = req.clone({
        setHeaders: {
          Authorization: `Bearer ${fullToken}`
        }
      })
    }

    return next.handle(req);
  }
}
