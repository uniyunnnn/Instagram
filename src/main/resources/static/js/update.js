// (1) 회원정보 수정
function update(userId,event) {
    event.preventDefault(); // form태그의 action경로를 비활성화 시킨다.
	//form태그가 가지고 있는 값을 다담아서 넘긴다.
    let data = $("#profileUpdate").serialize();
    
    console.log(data);

    $.ajax({
        type: "put",
        url: `/api/user/${userId}`,
        data: data,
        contentType: "application/x-www-form-urlencoded; charset=utf-8",
        dataType: "json"
    }).done(res => { //HttpStatus 상태코드 200번대
        console.log("update 성공", res)
        alert("회원정보가 성공적으로 수정되었습니다.");
        location.href = `/user/${userId}`;
    }).fail(error => {//HttpStatus 상태코드 200번대가 아닐때
        console.log("update 실패", error)
        alert("회원정보 수정에 실패하였습니다. 원인 : " + error.responseJSON.data);
    });
}
