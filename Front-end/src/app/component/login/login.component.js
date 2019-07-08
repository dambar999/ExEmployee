"use strict";
var __decorate = (this && this.__decorate) || function (decorators, target, key, desc) {
    var c = arguments.length, r = c < 3 ? target : desc === null ? desc = Object.getOwnPropertyDescriptor(target, key) : desc, d;
    if (typeof Reflect === "object" && typeof Reflect.decorate === "function") r = Reflect.decorate(decorators, target, key, desc);
    else for (var i = decorators.length - 1; i >= 0; i--) if (d = decorators[i]) r = (c < 3 ? d(r) : c > 3 ? d(target, key, r) : d(target, key)) || r;
    return c > 3 && r && Object.defineProperty(target, key, r), r;
};
exports.__esModule = true;
var core_1 = require("@angular/core");
var login_1 = require("src/app/model/login");
var glogin_1 = require("src/app/model/glogin");
var LoginComponent = /** @class */ (function () {
    function LoginComponent(loginService, sessionService, envService, ngZone, router, toasterService) {
        this.loginService = loginService;
        this.sessionService = sessionService;
        this.envService = envService;
        this.ngZone = ngZone;
        this.router = router;
        this.toasterService = toasterService;
    }
    LoginComponent.prototype.ngOnInit = function () {
        this.twitterLogoPic = this.envService.assetUrl + 'img/twitter.png';
        this.linkedInLogoPic = this.envService.assetUrl + 'img/linkedin-icon.ico';
        this.faceBookLogoPic = this.envService.assetUrl + 'img/facebook-logo.ico';
        this.accoliteLogoPic = this.envService.assetUrl + 'img/acco-logo.png';
        this.login = new login_1.Login();
        this.gLogin = new glogin_1.Glogin();
    };
    LoginComponent.prototype.ngAfterViewInit = function () {
        var _this = this;
        gapi.load('auth2', function () {
            _this.auth2 = gapi.auth2.init();
        });
    };
    /*
    validate and check password
    */
    LoginComponent.prototype.checkpassword = function () {
        var _this = this;
        this.email = this.login.email;
        this.sessionService.setLoggedIn(this.email);
        localStorage.setItem('glogin', 'false');
        var re = /\S+@\S+\.\S+/;
        if (!this.login.email) {
            this.message = 'email is empty';
            localStorage.setItem('message', this.message);
            localStorage.setItem('resp', '-1');
            this.toasterService.Success('-1');
            this.message1 = '';
            this.message2 = '';
            this.message3 = '';
        }
        else if (!this.login.password) {
            this.message1 = 'password is empty';
            localStorage.setItem('message', this.message1);
            localStorage.setItem('resp', '-1');
            this.toasterService.Success('-1');
            this.message = '';
            this.message2 = '';
            this.message3 = '';
        }
        else if (!re.test(String(this.login.email))) {
            this.message = 'Invalid email';
            localStorage.setItem('message', this.message);
            localStorage.setItem('resp', '-1');
            this.toasterService.Success('-1');
            this.message1 = '';
            this.message2 = '';
            this.message3 = '';
        }
        else {
            this.loginService.checkPassword(this.login).subscribe(function (response) {
                if (response) {
                    if (response.requestStatus === 1) {
                        localStorage.setItem('toaster', '1');
                        localStorage.setItem('message', response.message);
                        localStorage.setItem('resp', response.requestStatus);
                        _this.router.navigateByUrl('/profile');
                    }
                    else if (response.requestStatus === 0) {
                        console.log('from backend ' + response.message);
                        localStorage.setItem('message', response.message);
                        localStorage.setItem('resp', response.requestStatus);
                        var msg = localStorage.getItem('resp');
                        _this.toasterService.Success(msg);
                        _this.message3 = 'Invalid credentials';
                        _this.message = '';
                        _this.message1 = '';
                        _this.message2 = '';
                    }
                }
            });
        }
    };
    LoginComponent.prototype.googleLogin = function () {
        var _this = this;
        gapi.auth2.getAuthInstance().signIn({
            prompt: 'select_account'
        }).then(function (userData) {
            // get logged in user email id
            _this.gLogin.email = userData.w3.U3;
            _this.sessionService.setLoggedIn(_this.gLogin.email);
            localStorage.setItem('glogin', 'true');
            _this.ngZone.run(function () {
                _this.loginService.googleLogin(_this.gLogin).subscribe(function (response) {
                    if (response) {
                        localStorage.setItem('toaster', '1');
                        localStorage.setItem('message', response.message);
                        localStorage.setItem('resp', response.requestStatus);
                        if (response.requestStatus === 1) {
                            _this.loginService.checkhrLogin(_this.gLogin).subscribe(function (Response) {
                                if (Response) {
                                    if (Response.requestStatus === 1) {
                                        // localStorage.setItem('message', Response.message );
                                        // this.toasterService.Success(Response.requestStatus);
                                        localStorage.setItem('checkhr', '1');
                                        // localStorage.setItem('toaster', '1');
                                        // localStorage.setItem('message', Response.message);
                                        localStorage.setItem('resp', Response.requestStatus);
                                        _this.router.navigateByUrl('/listticket');
                                    }
                                }
                            });
                            _this.router.navigateByUrl('/profile');
                        }
                        else if (response.requestStatus === 0) {
                            _this.message3 = 'Invalid credentials';
                            _this.message = '';
                            _this.message1 = '';
                            _this.message2 = '';
                        }
                    }
                });
            });
        });
    };
    /*
    function to hide and show the password
    on click of fa fa-eye
    */
    LoginComponent.prototype.myFunctionShowPass = function () {
        var x = document.getElementById('password');
        if (x.getAttribute('type') === 'text') {
            x.setAttribute('type', 'password');
        }
        else {
            x.setAttribute('type', 'text');
        }
    };
    LoginComponent = __decorate([
        core_1.Component({
            selector: 'app-login',
            templateUrl: './login.component.html',
            styleUrls: ['./login.component.css',
                '../../styles/bootstrap.css',
                '../../styles/buttons.css',
                '../../styles/styles.css']
        })
    ], LoginComponent);
    return LoginComponent;
}());
exports.LoginComponent = LoginComponent;
