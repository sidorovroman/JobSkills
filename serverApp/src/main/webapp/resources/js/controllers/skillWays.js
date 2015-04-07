(function () {
    var app = angular.module('skillWays', []);

    /*
    *  todo!
    *  будет удобно обновлять данные ways
    *  если при запросах на создание и на обновление
    *  будут возвращаться
    *
    *
    *
    *
    *
    * **/
    app.controller("SkillWaysListCtrl", function ($scope, $http, $routeParams, $location) {
        var $modal = $("#modalSkillWay");

        getSkills($scope, $http, $routeParams.skillId);
        getWays($scope, $http, $routeParams.skillId);
        console.log($modal);
        $scope.SkillWaysForm = {};
        $scope.editWay = function (way) {

            setModalMode(true);
            $http.get('/wayToImproveSkill/' + way.id).
                success(function (data) {
                    console.log("get skill way with id: " + way.id + " success");
                    $scope.SkillWaysForm = data;
                    $modal.modal('show');

                }).
                error(function () {
                    console.log("get job with id: " + way.id + " failed");
                });
        };
        $scope.addWay = function ($scope, way) {
            setModalMode(false);
            $modal.modal('show');
        };

        $scope.addNew = function (skillId) {
            var dataObject = {
                caption: this.SkillWaysForm.caption,
                description: this.SkillWaysForm.description,
                link: this.SkillWaysForm.link,
                addDate:  new Date().getTime(),
                grade:  this.SkillWaysForm.grade,
                resourceType:  this.SkillWaysForm.resourceType,
                skills: [{"id":skillId}]
            };

            var responsePromise = $http.post("/wayToImproveSkill/add", dataObject, {});
            responsePromise.success(function (dataFromServer, status, headers, config) {
                console.log("add skill way success");
//                window.location.reload();
                if(dataFromServer.error!=null){
                    alert(dataFromServer.error);
                }
            });
            responsePromise.error(function (data, status, headers, config) {
                alert("Submitting form failed!");
            });


        };

        $scope.update = function (skillId) {
            var dataObject = {
                id: this.SkillWaysForm.id,
                caption: this.SkillWaysForm.caption,
                description: this.SkillWaysForm.description,
                link: this.SkillWaysForm.link,
                grade:  this.SkillWaysForm.grade,
                resourceType:  this.SkillWaysForm.resourceType,
                skills: [{id:skillId}]
            };

            var responsePromise = $http.put("/wayToImproveSkill/update", dataObject);
            responsePromise.success(function (dataFromServer, status, headers, config) {
                console.log("update success");
//                window.location.reload();
                if(dataFromServer.error!=null){
                    alert(dataFromServer.error);
                }

            });
            responsePromise.error(function (data, status, headers, config) {
                alert("update error status "+status);
            });
        };
        $('#modalSkillWay').on('hidden.bs.modal', function (e) {
            $(this).find('form')[0].reset();
        });
        function setModalMode(editMode) {
            var $modal = $("#modalSkillWay");

            var btnAdd = $modal.find(".btn-add");
            var btnUpdate = $modal.find(".btn-update");

            if(editMode){
                btnAdd.hide();
                btnUpdate.show();
                $scope.SkillWaysForm.title = "Редактирование";
            }else{
                btnAdd.show();
                btnUpdate.hide();
                $scope.SkillWaysForm.title = "Добавление";
            }
        }
    });

//    function prepareSkillsToSend(skillsId) {
//        var skillData =[];
//        for(var i in skillsId){
//            skillData.push({"id":skillsId[i]});
//        }
//        return skillData;
//    }

    function getSkills($scope, $http, skillId) {
        $http.get('/requiredSkill/'+skillId).
            success(function (data) {
                $scope.skill = data;
                $.material.init();
            });
    }

    function getWays($scope, $http, skillId) {

        $http.get('/wayToImproveSkill/'+skillId+'/list').
            success(function (data) {
                console.log("ways: "+data);
                $scope.ways = data;

            }).
            error(function () {
                alert("Fail");
            });
    }

})();