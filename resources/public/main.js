/*global $, prompt */
var $calendar = $('#calendar').fullCalendar({
    header: {
	left: 'prev,next today',
	center: 'title',
	right: 'month,agendaWeek,agendaDay'
    },
    selectable: true,
    selectHelper: true,
    defaultView: 'agendaWeek',
    select: function(start, end, allDay) {
	var title = prompt('Event Title:');
	if (title) {
            $calendar.fullCalendar('renderEvent',
                                  {
                                      title: title,
                                      start: start,
                                      end: end,
                                      allDay: allDay
                                  },
                                  true
                                 );
	}
	$calendar.fullCalendar('unselect');
    },
    editable: true
});


$('.datepicker').datepicker();

$('#go').on('click', function () {
    $('#result').removeClass('hide');

    $.jqplot('graph1', [
        [[2,1], [4,2], [6,3], [3,4]],
        [[5,1], [1,2], [3,3], [4,4]],
        [[4,1], [7,2], [1,3], [2,4]]], {
            seriesDefaults: {
                renderer:$.jqplot.BarRenderer,
                pointLabels: { show: true, location: 'e', edgeTolerance: -15 },
                shadowAngle: 135,
                rendererOptions: {
                    barDirection: 'horizontal'
                }
            },
            axes: {
                yaxis: {
                    renderer: $.jqplot.CategoryAxisRenderer
                }
            }
        });

    $.jqplot ('graph2', [[
        ['Heavy Industry', 12],['Retail', 9], ['Light Industry', 14],
        ['Out of home', 16],['Commuting', 7], ['Orientation', 9]
    ]],
              {
                  seriesDefaults: {
                      renderer: $.jqplot.PieRenderer,
                      rendererOptions: {
                          showDataLabels: true
                      }
                  },
                  legend: { show:true, location: 'e' }
              }
             );
});
