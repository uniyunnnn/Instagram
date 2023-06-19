// (1) 회원정보 수정
function update(userId) {
	//form태그가 가지고 있는 값을 다담아서 넘긴다.
    let data = $("#profileUpdate").serialize();
    
    console.log(data);

    $.ajax({
        type: "put",
        url: `/api/user/${userId}`,
        data: data,
        contentType: "application/x-www-form-urlencoded: charset=utf-8",
        dataType: "json"
    }).done(res => {
        console.log("update 성공", res)
    }).fail(error => {
        console.log("update 실패", error)
    });
}
