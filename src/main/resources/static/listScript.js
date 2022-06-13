$('.btn-danger').click(function() {
	var id = $(this).attr("data-id");
	$.ajax({
		url: '/deleteItem/' + id,
		type: 'DELETE',
		success: function(result) {
			alert('Item with id ' + id + ' deleted successfully');
			window.location.reload();
		}
	});
});

$('.btn-success').click(function() {
	var id = $(this).attr("data-id");
	$('#id').val(id);
});

$('#update').click(function() {
	var id = $('#id').val();
	var count = $('#count').val();
	var operationType = $('#operation').val();
	var api = '';
	if (operationType == 'add') {
		api = '/addStock';
	} else {
		api = '/deleteStock';
	}
	$.ajax({
		url: api,
		data: {
			id: id,
			count: count
		},
		type: 'PUT',
		success: function(data) {
			alert('Item with id ' + id + ' updated successfully');
			window.location.reload();
		},
		error: function(data) {
			alert('Error updating the inventory');
		}
	});
});