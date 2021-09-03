let items = [
    {
        "id": 1,
        "description": "Add new task",
        "done": false,
        "user": {
            "name": "your"
        }
    }
];

$(function () {
    $('#input-spinner-div').hide();
    getItems();

    // add new item handler
    $('#item-form').keyup(e => {
        if (e.keyCode !== 13) {
            return
        }
        postItem({
            description: e.currentTarget.value
        })
        e.currentTarget.value = ''
    });

    // toggle 'show done' click handler
    $('#toggleShowDone').click(e => {
        redraw(filter(items))
    })

    // item click handler
    $(document).on('click', '#items ul li input', e => {
        toggleItem(e.currentTarget.id)
    });
})

function toggleItem(id) {
    let item = items.find(item => item.id === Number.parseInt(id));
    item.done = !item.done;
    postItem(item)
}

function redraw(items) {
    $('#items ul li').remove();
    $.each(items, (_, item) => {
        $('#items ul').append(printItemRow(item))
    });
}

function filter(items) {
    let showDone = $('#toggleShowDone:checked').val() === 'true';
    return showDone ? items : items.filter(item => !item.done)
}

function printItemRow(item) {
    return '<li class="list-group-item">' +
        `<input id=${item.id} class="form-check-input me-1" type="checkbox" aria-label="..."` +
        (item.done ? ' checked' : '') +
        `>${item.description} [by ${item.user.name}]` +
        '</li>';
}

function getItems() {
    $.ajax({
            type: 'GET',
            crossdomain: true,
            url: 'http://localhost:8080/todo/items',
            dataType: 'json',
            complete: () => {
                $('#list-spinner').hide();
            },
            success: data => {
                items = data.slice();
                redraw(filter(items))
            },
            error: jqXHR => console.log(jqXHR.responseText)
        }
    )
}

function postItem(item) {
    $.ajax({
        type: "POST",
        crossdomain: true,
        url: 'http://localhost:8080/todo/items',
        dataType: 'json',
        data: JSON.stringify(item),
        beforeSend: () => {
            $('#input-spinner-div').show();
        },
        complete: () => {
            $('#input-spinner-div').hide();
        },
        success: item => {
            let idx = items.findIndex(el => el.id === item.id)
            if (idx === -1) {
                items.push(item)
            } else {
                items[idx] = item
            }
            redraw(filter(items))
        },
        error: jqXHR => console.log(jqXHR.responseText)
    });
}

