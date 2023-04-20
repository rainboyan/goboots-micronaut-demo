<!DOCTYPE html>
<html>
    <head>
        <meta name="layout" content="main" />
        <g:set var="entityName" value="${message(code: 'genre.label', default: 'Genre')}" />
        <title><g:message code="default.edit.label" args="[entityName]" /></title>
    </head>
    <body>
    <div id="content" role="main">
        <div class="container">
            <section class="row">
                <a href="#edit-genre" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
                <div class="col-12" role="navigation">
                    <ul class="nav nav-pills">
                        <li class="nav-item">
                            <a class="nav-link" href="${createLink(uri: '/')}">
                                <i class="bi bi-house-fill"></i><g:message code="default.home.label"/>
                            </a>
                        </li>
                        <li class="nav-item">
                            <g:link class="nav-link" action="index">
                                <i class="bi bi-journals"></i><g:message code="default.list.label" args="[entityName]" />
                            </g:link>
                        </li>
                        <li class="nav-item">
                            <g:link class="nav-link" action="create">
                                <i class="bi bi-journal-plus"></i><g:message code="default.new.label" args="[entityName]" />
                            </g:link>
                        </li>
                    </ul>
                </div>
            </section>
            <section class="row">
                <div id="edit-genre" class="col-12 scaffold scaffold-edit" role="main">
                    <h1><g:message code="default.edit.label" args="[entityName]" /></h1>
                    <g:render template="/templates/includes/message" model="[entity: this.genre]" />
                    <g:form resource="${this.genre}" method="PUT">
                        <g:hiddenField name="version" value="${this.genre?.version}" />
                        <fieldset class="form">
                            <div class="row">
                                <div class="col-3"><label>Name</label></div>
                                <div class="col-9">
                                    <g:textField name="name" value="${genre.name}" class="form-control col-4" />
                                </div>
                            </div>
                        </fieldset>
                        <fieldset class="buttons offset-3">
                            <button class="btn btn-primary">
                                <i class="bi bi-journal-check"></i><g:message code="default.button.update.label" default="Update" />
                            </button>
                        </fieldset>
                    </g:form>
                </div>
            </section>
        </div>
    </div>
    </body>
</html>
