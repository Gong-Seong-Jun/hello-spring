//getElementById(id): ID를 통해 HTML 요소 선택
//getElementsByTagName(name): 태그 이름으로 HTML 요소 선택
//getElementsByClassName(name): 클래스 이름으로 HTML 요소 선택
//querySelector(selector): CSS 선택자를 사용하여 HTML 요소 선택
//querySelectorAll(selector): CSS 선택자를 사용하여 HTML 요소 목록 선택
//createElement(name): HTML 요소 생성
//createTextNode(text): 텍스트 노드 생성
//appendChild(child): 자식 노드를 부모 노드에 추가
//removeChild(child): 자식 노드를 부모 노드에서 제거
//insertBefore(new, ref): 새 노드를 기존 노드 앞에 삽입
//replaceChild(new, old): 새 노드로 기존 노드를 교체
//setAttribute(name, value): HTML 요소 속성 설정
//getAttribute(name): HTML 요소 속성 값 가져오기
//removeAttribute(name): HTML 요소 속성 제거

function join() {
    let id = document.getElementByID("id").value;
    let password = document.getElementById("password").value;
    let username = document.getElementById("username").value;
    let age = document.getElementById("age").value;


    let data = new Map();
    data.set("id", id);
    data.set("password", password);
    data.set("username", username);
    data.set("age", age);

    let jsonData = JSON.stringify(Object.fromEntries(data));

    let hiddenInput = document.getElementById("submit");
    hiddenInput.type = "hidden";
    hiddenInput.name = "jsonData";
    hiddenInput.value = jsonData;

    let form = document.getElementById("joinForm");
    form.action = "/members/new/";
    form.method = "post";
    form.body = jsonData;
};

