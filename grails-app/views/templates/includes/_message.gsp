    <g:if test="${flash.message}">
    <div class="alert alert-success" role="status"><i class="bi bi-info-circle"></i> ${flash.message}</div>
    </g:if>
    <g:hasErrors bean="${entity}">
    <div class="alert alert-danger" role="alert">
        <ul class="errors" role="alert">
            <g:eachError bean="${entity}" var="error">
            <li <g:if test="${error in org.springframework.validation.FieldError}">data-field-id="${error.field}"</g:if>>
                <i class="bi bi-exclamation-circle"></i> <g:message error="${error}"/></li>
            </g:eachError>
        </ul>
    </div>
    </g:hasErrors>