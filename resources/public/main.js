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
