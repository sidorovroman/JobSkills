(function () {
    var app = angular.module('skillWays', []);

    app.controller("SkillWaysListCtrl", function ($scope, $http, $routeParams, $location) {
        $http.get('/wayToImproveSkill/'+$routeParams.skillId+'/list').
            success(function (data) {
                console.log("ways: "+data);
                $scope.ways = data;
            }).
            error(function () {
                alert("Fail");
            });
        $scope.add = function () {
            $location.path('/jobs/'+$routeParams.jobId+"/skills/"+$routeParams.skillId+"/ways/add");
        }
    })

    function prepareSkillsToSend(skillsId) {
        var skillData =[];
        for(var i in skillsId){
            skillData.push({"id":skillsId[i]});
        }
        return skillData;
    }

    app.controller("AddSkillWaysCtrl", function ($scope, $location, $http, $routeParams) {
        $scope.allJobs = [];
        $http.get('/jobs/allJobs').
            success(function (data) {
                $scope.allJobs = data;
                $scope.allJobs = data;
                setTimeout(function(){//todo dirty hack, multiselect применяется раньше чем отрисовывается select,пока застрял на этом моменте
                    $("#inputSkillWaySkills").multiselect();
                },1000);
            }).
            error(function () {
                console.log("Fail load all jobs");
            });

        $scope.SkillWaysForm = {};
        $scope.save = function () {
            var dataObject = {
                caption: this.SkillWaysForm.caption,
                description: this.SkillWaysForm.description,
                link: this.SkillWaysForm.link,
                addDate:  new Date().getTime(),
                grade:  this.SkillWaysForm.grade,
                resourceType:  this.SkillWaysForm.resourceType,
                skills: prepareSkillsToSend(this.SkillWaysForm.skills)
            };

            var responsePromise = $http.post("/wayToImproveSkill/add", dataObject, {});
            responsePromise.success(function (dataFromServer, status, headers, config) {
                console.log("add skill way success");
                window.history.back();
            });
            responsePromise.error(function (data, status, headers, config) {
                alert("Submitting form failed!");
            });
        }
    });
    app.controller("EditSkillWaysCtrl", function ($scope, $location, $http, $routeParams) {

        $scope.SkillForm = {};
        $http.get('/wayToImproveSkill/' + $routeParams.wayId).
            success(function (data) {
                console.log("success");
                console.log("get skill way with id: " + $routeParams.wayId + " success");
                $scope.SkillWaysForm = data;
            }).
            error(function () {
                console.log("get job with id: " + $routeParams.wayId + " failed");
            });

        $scope.save = function () {
            var dataObject = {
                id: $routeParams.wayId,
                caption: this.SkillWaysForm.caption,
                description: this.SkillWaysForm.description,
                link: this.SkillWaysForm.link,
                grade:  this.SkillWaysForm.grade,
                resourceType:  this.SkillWaysForm.resourceType,
                skills: [{id:this.SkillWaysForm.skills}]

            };

            var responsePromise = $http.put("/wayToImproveSkill/update", dataObject);
            responsePromise.success(function (dataFromServer, status, headers, config) {
                console.log("update success");
                window.history.back();
            });
            responsePromise.error(function (data, status, headers, config) {
                alert("update error status "+status);
            });
        }
    });
})();