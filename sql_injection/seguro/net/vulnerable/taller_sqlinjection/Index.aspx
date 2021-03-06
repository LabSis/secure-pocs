﻿<%@ Page Language="C#" AutoEventWireup="true" CodeBehind="Index.aspx.cs" Inherits="taller_sqlinjection.Index" %>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="utf-8"/>
        <link rel="shortcut icon" href="./Assets/img/favicon.png" />
        <title>NSA CSS | Projects</title>
        <link href="./Assets/css/lib/bootstrap-3.3.7/css/bootstrap.min.css" rel="stylesheet"/>
        <script src="./Assets/js/lib/jquery-3.2.1.min.js" ></script>
        <script src="./Assets/css/lib/bootstrap-3.3.7/js/bootstrap.min.js" ></script>
        <link href="./Assets/css/style.css" rel="stylesheet"/>
        <script src="./Assets/js/index.js" ></script>
    </head>
    <body>
        <header id="header">
            <nav class="navbar navbar-default">
                <div class="container">
                    <div class="navbar-header">
                        <button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#bs-example-navbar-collapse-1">
                            <span class="icon-bar"></span>
                            <span class="icon-bar"></span>
                            <span class="icon-bar"></span>
                        </button>
                        <a class="navbar-brand" href="#"></a>
                    </div>
                    <div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
                        <ul class="nav navbar-nav navbar-right">
                            <li><a href="#">Projects</a></li>
                            <li class="dropdown">
                                <a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-haspopup="true" >
                                    Edward Snowden
                                    <span class="caret"></span>
                                </a>
                                <ul class="dropdown-menu">
                                    <li><a href="#">Messages</a></li>
                                    <li><a href="#">User logs</a></li>
                                    <li class="divider"></li>
                                    <li><a href="#">Log out</a></li>
                                </ul>
                            </li>
                        </ul>
                    </div>
                </div>
            </nav>
        </header>
        <main id="content">
            <div id="content-header">
                <div class="container">
                    <div class="row">
                        <div class="col-sm-12">
                            <h1>
                                Projects
                            </h1>
                            <p>
                                This section shows a list of all the projects you as agent are involved in.
                            </p>
                        </div>
                    </div>
                </div>
            </div>
            <div id="body-content">
                <div class="container">
                    <div class="row">
                        <div class="col-sm-12">
                            <form class="form-inline" id="form-filter-search">
                                <div class="form-group">
                                    <label for="slc-project-type">Filter displayed projects by selecting a project type: </label>
                                    <br class="visible-sm-block visible-xs-block"/>
                                    <select class="form-control" id="slc-project-type">
                                        <option value="-1">Project type</option>
                                        <option value="1">Spying</option>
                                        <option value="2">Targeted attack</option>
                                        <option value="3">APT</option>
                                    </select>
                                    <button type="button" class="btn btn-default" id="btn-filter-search">Filter search</button>
                                </div>
                            </form>
                            <div class="alert alert-warning hidden" id="search-result-message">
                                Results
                            </div>
                            <table class="table table-hover table-bordered table-condensed" id="table-projects">
                                <thead>
                                    <tr>
                                        <th>Code</th>
                                        <th>Name</th>
                                        <th>Type</th>
                                        <th>Access level</th>
                                        <th>Owner</th>
                                    </tr>
                                </thead>
                                <tbody>
                                </tbody>
                            </table>
                        </div>
                    </div>
                </div>
            </div>
        </main>
        <footer>
            <div class="container">
                <div class="row">
                    <div class="col-sm-12 text-center">
                        <img src="./Assets/img/footerNSASeal.png" alt=""/>
                        <img src="./Assets/img/footerCSSSeal.png" alt=""/>
                    </div>
                </div>
            </div>
        </footer>
    </body>
</html>
