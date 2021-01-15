/**
 * 抽奖的顺序是从小奖到大奖
 * @author luyu
 * @version v1.0
 * @type {string}
 */

var max;
var second;
var allPerson="";
var reportPerson="";
var remainPerson="";
var prize;
var prizeSize;
var PrizeLevelList;
var leaderArr;
var prizeWins=[];
//抽奖次数
var index=0;
var p = new Map();
//初始化抽奖人员名单
$.ajax({
    url:"/user/queryAllName",
    type:"get",
    dataType:"json",
    success:function (result) {
        var student = result.student;
        // var teacher = result.teacher;
        for(var i=0;i<student.length;i++){
            allPerson=allPerson+student[i].userNo+";";
            p.set(student[i].userNo,student[i].userName);
            if(student[i].report=="是"){
                reportPerson = reportPerson+student[i].userNo+";";
            }
        }
        // for(var i=0;i<teacher.length;i++){
        //     leaderArr[i]=teacher[i].userNo;
        //     p.set(teacher[i].userNo,teacher[i].userName);
        // }
        //分别筛出全部人和报告的人
        allPerson=allPerson.slice(0,allPerson.length-1);
        reportPerson = reportPerson.slice(0,reportPerson.length-1);

    }
});
//初始化奖品列表
$.ajax({
    url:"/prize/queryAllNoLimit",
    type:"GET",
    dataType:"json",
    success:function (result) {
        //获取所有的奖品数量
        prizeSize=result.prizeCount;
        prize=result.data;
        if(prizeSize==0){
            $("#txtNum").attr("placeholder", "请放置奖品！");
        }else{
            PrizeLevelList=Object.keys(prize).sort().reverse();
            var peopleCount=prize[PrizeLevelList[0]].length;
            max=(Object.keys(prize).sort().reverse())[0];
            second=max-1;
            $("#txtNum").attr("placeholder", "输入"+prizeLevel(PrizeLevelList[index])+"抽奖人数("+peopleCount+")");
        }
    }
});

//领导人员名单

//未中奖人员名单

//中奖人员名单
var luckyMan = [];
var timer;//定时器
var times = 1;//抽奖次数,如果不是第一次，不加粗显示领导姓名

$(function () {
    iconAnimation();

    //开始抽奖
    $("#btnStart").on("click", function () {

        //判断是开始还是结束
        if ($("#btnStart").text() === "开始") {

            if(PrizeLevelList[index]==max){
                //设置所有为抽奖人员
                remainPerson = allPerson.toString().split(";");
            }else if(PrizeLevelList[index]==second){
                //设置报告人员为抽奖人员
                remainPerson = reportPerson.toString().split(";");
            }

            if(index==prizeSize){
                showDialog("奖品已经全部抽光了");
                return false;
            }
            if (!$("#txtNum").val()) {
                showDialog("请输入中奖人数");
                return false;
            }

            if($("#txtNum").val()!=prize[PrizeLevelList[index]].length){
                showDialog("请输入合适的中奖人数");
                return false;
            }

            if ($("#txtNum").val() > 49) {
                showDialog("一次最多只能输入49人");
                return false;
            }
            if ($("#txtNum").val() > remainPerson.length) {
                showDialog("当前抽奖人数大于奖池总人数<br>当前抽奖人数：<b>" + $("#txtNum").val() + "</b>人,奖池人数：<b>" + remainPerson.length + "</b>人");
                return false;
            }
            $("#result").fadeOut();
            //显示动画框，隐藏中奖框
            $("#luckyDrawing").show().next().addClass("hide");
            move();
            $("#btnStart").text("停止");
            $("#bgLuckyDrawEnd").removeClass("bg");
        }
        else {
            $("#btnStart").text("开始");//设置按钮文本为开始
            var luckyDrawNum = $("#txtNum").val();
            startLuckDraw();//抽奖开始

            $("#luckyDrawing").fadeOut();
            clearInterval(timer);//停止输入框动画展示
            $("#luckyDrawing").val(luckyMan[luckyMan.length - 1]);//输入框显示最后一个中奖名字
            $("#result").fadeIn().find("div").removeClass().addClass("p" + luckyDrawNum);//隐藏输入框，显示中奖框
            $("#bgLuckyDrawEnd").addClass("bg");//添加中奖背景光辉
            var param = JSON.stringify(prizeWins);
            if(index==prizeSize){//抽奖结束
                $("#txtNum").attr("placeholder", "已无奖品,抽奖结束");
                //上传中奖数据
                $.ajax({
                    url:"/prizeWin/savePrizeInfo",
                    type:"post",
                    dataType:"json",
                    traditional:true,
                    data:{"prizeWinInfos":param},
                    success:function (result) {
                        showDialog("奖品已经抽完");
                    }
                });
            }else{
                $("#txtNum").attr("placeholder", "输入"+prizeLevel(PrizeLevelList[index])+"抽奖人数(" + prize[PrizeLevelList[index]].length + ")");
            }
        }
    });

    $("#btnReset").on("click", function () {
        //确认重置对话框
        var confirmReset = false;
        showConfirm("确认重置吗？所有已中奖的人会重新回到抽奖池！", function () {
            //熏置未中奖人员名单
            remainPerson = allPerson.toString().split(";");
            index=0;
            prizeWins=[];
            //中奖人数框置空
            var peopleCount=prize[(Object.keys(prize).sort().reverse())[0]].length;

            $("#txtNum").attr("placeholder", "输入"+prizeLevel(PrizeLevelList[index])+"抽奖人数("+peopleCount+")");
            $("#showName").val("");
            //隐藏中奖名单,然后显示抽奖框
            $("#result").fadeOut();//.prev().fadeIn()
            $("#bgLuckyDrawEnd").removeClass("bg");//移除背景光辉
            console.log(times);
        });
    });
});

//抽奖主程序
function startLuckDraw() {
    var shows=[];
    //抽奖人数
    var luckyDrawNum = $("#txtNum").val();
    if (luckyDrawNum > remainPerson.length) {
        alert("抽奖人数大于奖池人数！请修改人数。或者点重置开始将新一轮抽奖！");
        return false;
    }
    //初始化奖品列表
    var prizeList=[];

    for(var i=0;i<prize[PrizeLevelList[index]].length;i++){
        prizeList.push(prize[PrizeLevelList[index]][i].prizeName+"-"+prize[PrizeLevelList[index]][i].prizeType+"-"+prize[PrizeLevelList[index]][i].prizeDescribe);
    }
    //随机中奖人
    var randomPerson = getRandomArrayElements(remainPerson, luckyDrawNum);
    var prizeInfo = getRandomArrayElements(prizeList,luckyDrawNum);

    for(var i=0;i<randomPerson.length;i++) {
        var prizeName=prizeInfo[i].split("-")[0];
        var prizeType=prizeInfo[i].split("-")[1];
        var prizeDescribe=prizeInfo[i].split("-")[2];
        var prizeLevel=prize[PrizeLevelList[index]][0].prizeLevel;
        prizeWins.push({"userNo":randomPerson[i],"userName":p.get(randomPerson[i]),"prizeLevel":prizeLevel,"prizeName":prizeName,"prizeType":prizeType,"prizeDescribe":prizeDescribe});
        shows[i] = p.get(randomPerson[i])+"-"+prizeName;
    }
    var tempHtml = "";
    index++;
    $.each(shows, function (i, person) {
        var sizeStyle = "";
        if (person.length > 3) {
            sizeStyle = " style=font-size:" + 3 / person.length + "em";
        }
		tempHtml += "<span><span " + sizeStyle + ">" + person + "</span></span>";
    });
    $("#result>div").html(tempHtml);
    //剩余人数剔除已中奖名单
    remainPerson = remainPerson.delete(randomPerson);
    //中奖人员
    luckyMan = luckyMan.concat(shows);
    //设置抽奖人数框数字为空
    $("#txtNum").val("");
}

//跳动的数字
function move() {
    var shows=[];
    for(var i=0;i<remainPerson.length;i++) {
        shows[i]=p.get(remainPerson[i]);
    }
    var $showName = $("#showName"); //显示内容的input的ID
    var interTime = 30;//设置间隔时间
    timer = setInterval(function () {
        var i = GetRandomNum(0, shows.length);
        $showName.val(shows[i]);//输入框赋值
    }, interTime);
}

//顶上的小图标，随机动画
function iconAnimation() {
    var interTime = 200;//设置间隔时间
    var $icon = $("#iconDiv>span");
    var arrAnimatoin = ["bounce", "flash", "pulse", "rubberBand", "shake", "swing", "wobble", "tada"];
    var timer2 = setInterval(function () {
        var i = GetRandomNum(0, $icon.length);
        var j = GetRandomNum(0, arrAnimatoin.length);
        //console.log("i:" + i + ",j:" + j);
        $($icon[i]).removeClass().stop().addClass("animated " + arrAnimatoin[j]);//输入框赋值
    }, interTime);

}

function prizeLevel(i) {
    if(i==0){
        return "特等奖";
    }
    else if(i==1){
        return "一等奖";
    }else if(i==2){
        return "二等奖";
    }else if(i==3){
        return "三等奖";
    }else if(i==4){
        return "四等奖";
    }else if(i==5){
        return "五等奖";
    }else if(i==6){
        return "六等奖";
    }else{
        return "纪念奖";
    }

}