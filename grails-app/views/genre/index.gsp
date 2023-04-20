<!DOCTYPE html>
<html>
    <head>
        <meta name="layout" content="main" />
        <g:set var="entityName" value="${message(code: 'genre.label', default: 'Genre')}" />
        <title><g:message code="default.list.label" args="[entityName]" /></title>
    </head>
    <body>
    <div id="content" role="main">
        <div class="container">
            <section class="row">
                <a href="#list-genre" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
                <div class="col-12" role="navigation">
                    <ul class="nav nav-pills">
                        <li class="nav-item">
                            <a class="nav-link" href="${createLink(uri: '/')}">
                                <i class="bi bi-house-fill"></i><g:message code="default.home.label"/>
                            </a>
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
                <div id="list-genre" class="col-12 scaffold scaffold-list" role="main">
                    <h1><g:message code="default.list.label" args="[entityName]" /></h1>
                    <g:render template="/templates/includes/message" model="[entity: this.genre]" />
                    <table class="table table-bordered">
                        <thead>
                            <tr>
                                <g:sortableColumn property="name" title="Name" />
                            </tr>
                        </thead>
                        <tbody>
                            <g:each in="${genreList}" var="genre" status="i">
                                <tr class="${(i % 2) == 0 ? 'even' : 'odd'}">
                                    <td><g:link method="GET" action="show" id="${genre.id}">${genre.name}</g:link></td>
                                </tr>
                            </g:each>
                        </tbody>
                    </table>

                    <g:if test="${tagCount > params.int('max')}">
                    <nav aria-label="Page navigation">
                        <bs:paginate total="${tagCount ?: 0}" useIcons="true" align="center" />
                    </nav>
                    </g:if>
                </div>
            </section>
        </div>
    </div>
    </body>
</html>