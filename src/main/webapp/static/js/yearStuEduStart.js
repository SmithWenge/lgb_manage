// 基于准备好的dom，初始化echarts实例
var myChart = echarts.init(document.getElementById('yearStuEduStart'));

var option = {
    title : {
        text: '学员入学时间',
        subtext: '对比',
        x:'center'
    },
    tooltip : {
        trigger: 'item',
        formatter: "{a} <br/>{b} : {c} ({d}%)"
    },
    legend: {
        orient: 'vertical',
        left: 'left',
        data: ['直接访问','邮件营销','联盟广告','视频广告','搜索引擎']
    },
    series : [
        {
            name: '访问来源',
            type: 'pie',
            radius : '55%',
            center: ['50%', '60%'],
            data:[
                {value:335, name:'直接访问'},
                {value:310, name:'邮件营销'},
                {value:234, name:'联盟广告'},
                {value:135, name:'视频广告'},
                {value:1548, name:'搜索引擎'}
            ],
            itemStyle: {
                emphasis: {
                    shadowBlur: 10,
                    shadowOffsetX: 0,
                    shadowColor: 'rgba(0, 0, 0, 0.5)'
                }
            }
        }
    ]
};

// 使用刚指定的配置项和数据显示图表。
myChart.setOption(option);

$(function () {
    $.ajax({
        type: 'post',
        contentType: 'application/json',
        dataType: 'json',
        url: '/lgbmanage/admin/count/all.action',
        success: function (result) {
            console.log($.toJSON(result));
        }
    });
})