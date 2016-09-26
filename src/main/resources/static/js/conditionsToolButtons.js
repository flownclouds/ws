/**
 * Created by izayl on 16/9/24.
 */
$(function () {
  var userInfo = getUserInfo();
  var buttons = ["导出", "导出汇总"];
  var conditions = {
    "泵站": "bangzhan",
    "大口井": "dakoujin",
    "渡槽": "ducao",
    "管滴灌": "guandi",
    "涵洞": "handong",
    "河道": "hedao",
    "深水井": "shenshuijin",
    "水厂": "shuichang",
    "水电站": "shuidianzhan",
    "水塘": "shuitang",
    "水闸": "shuizha",
    "塘坝": "tangba"
  };
  var category = $("#type").html();
  if (userInfo.rolesId == 2 || userInfo.rolesId == 1) {
    buttons.unshift("添加", "删除");
  }
  console.log(buttons);
  var bindButtonsEvent = function () {
    var data = {
      token: userInfo.token,
      userId: userInfo.userId
    }
    $("button[data-type='导出']").click(function (e) {
      var $list = $("input[type=checkbox]:checked");
      if ($list.length < 1) {
        alert("请选中所需要导出的文件");
        return false;
      }
      $list.each(function (i, el) {
        data.projectId = $(el).val();
        $.post("/excel/exportSummary", data, function (res) {
          console.log("downloading summary....");
        })
      })
    });
    $("button[data-type='导出汇总']").click(function (e) {
      data.category = conditions.category;
      $.post("/excel/exportSummary", data);
    });
    $("button[data-type='添加']").click(function (e) {
      window.location.href = "../addConditionsDetails/"+conditions[category]+".html";
    });
    $("button[data-type='删除']").click(function (e) {
      var $list = $("input[type=checkbox]:checked");
      if ($list.length < 1) {
        alert("请选中所需要删除的文件");
        return false;
      }
      $list.each(function (i,el) {
        data.projectId = $(el).val();
        $.post("/project/deleteProject", data, function (res) {
          console.log("deleteProject....");
          $(el).parents("tr").remove();
        })
      })
    })
  }
  var renderButtons = function(buttons){
    var html = '<div class="btn-toolbar"> ';
    buttons.map(function (button) {
      html += '<button type="button" data-type="' + button + '" class="btn btn-sm btn-success">' + button + '</button> ';
    });
    html += '</div>';
    console.info($(html));
    $("table").before($(html));

    bindButtonsEvent();
  };

  renderButtons(buttons);
})
