<%@ page contentType="text/html; charset=UTF-8"%>
<c:if test="${not empty actionMessages}">
    <span class="message" id="actionMessages">
        <c:forEach var="msg" items="${actionMessages}">
            <script>alert('${msg}');</script>
        </c:forEach>
    </span>
</c:if>
<c:if test="${not empty actionErrors}">
    <span class="error" id="actionErrors">
        <c:forEach var="err" items="${actionErrors}">
        	<script>alert('${err}');</script>
        </c:forEach>
    </span>
</c:if>
 
