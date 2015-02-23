(function () {
    $('#login-form').on('click', function(e){
        e.preventDefault();

        if(e.target.value == 'login'){
            console.log($(this).serialize());
            $.ajax({
                url: '/j_spring_security_check',
                type: 'POST',
                data: $(this).serialize(),
                success: function(response){
                    alert('success: '+response);
                },
                error: function(response){
                    alert('error: '+response);

                }
            });

        }else if(e.target.value == 'registration'){
            console.log($(this).serialize());
            $.ajax({
                url: '/register',
                type: 'POST',
                data: $(this).serialize(),
                success: function(response){
                    alert('success: '+response);
                },
                error: function(response){
                    alert('error: '+response);
                }
            });

//            $http.post('/register',dataObject)
//                .success(function(response){
//                    if(response.error==null){
//                        console.log("reg success");
//                        $location.path('/');
//                    }else{
//                        alert(response.error);
//                    }
//                });
        }


    });
})();