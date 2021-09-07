let state = {
    items: [
        {
            "id": 1,
            "description": "Add new task",
            "done": false,
            "user": {
                "name": "your"
            },
            "categories": [1, 2]
        }
    ],
    categories: [
        {
            "id": 1,
            "name": "cat1"
        },
        {
            "id": 2,
            "name": "cat2"
        }
    ]
}

$(function () {
    $('#input-spinner-div').hide();
    getItems();

    // add new item handler
    $('#item-form').keyup(e => {
        if (e.keyCode !== 13) {
            return
        }
        postItem({
            categories: getSelectedCategories(),
            description: e.currentTarget.value,
            user: {
                id: 1
            }
        })
        e.currentTarget.value = ''
    });

    // toggle 'show done' click handler
    $('#toggleShowDone').click(e => {
        redraw(filter(state))
    })

    function getSelectedCategories() {
        return $('#categories option:selected').toArray().map(el => ({"id": el.value}));
    }

    // item click handler
    $(document).on('click', '#items ul li input', e => {
        toggleItem(e.currentTarget.id)
    });
})

function toggleItem(id) {
    let item = state.items.find(item => item.id === Number.parseInt(id));
    item.done = !item.done;
    postItem(item)
}

function redraw(state) {
    $('#items ul li').remove();
    $.each(state.items, (_, item) => {
        $('#items ul').append(printItemRow(item))
    });

    $('#categories option').remove();
    $.each(state.categories, (_, category) => {
        $('#categories').append(printCategoryRow(category))
    });
}

function filter(state) {
    let showDone = $('#toggleShowDone:checked').val() === 'true';
    state.items.sort((a, b) => (a.done === b.done) ? 0 : a.done ? 1 : -1);
    return showDone ? state : {
        categories: state.categories,
        items: state.items.filter(item => !item.done)
    }
}

function printItemRow(item) {
    return '<li class="list-group-item">' +
        `<input id=${item.id} class="form-check-input me-1" type="checkbox" aria-label="..."` +
        (item.done ? ' checked' : '') +
        `>${item.description} [by ${item.user.name}]` +
        '</li>';
}

function printCategoryRow(category) {
    return `<option value=${category.id}>${category.name}</option>`;
}

function getItems() {
    $.ajax({
            type: 'GET',
            crossdomain: true,
            url: 'http://localhost:8080/todo/items',
            dataType: 'json',
            beforeSend: () => {
                $('#list-spinner').show();
            },
            complete: () => {
                $('#list-spinner').hide();
            },
            success: data => {
                //copy arrays to state
                state.items = data.items.slice();
                state.categories = data.categories.slice();
                redraw(filter(state))
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
            let idx = state.items.findIndex(el => el.id === item.id)
            if (idx === -1) {
                state.items.push(item)
            } else {
                state.items[idx] = item
            }
            redraw(filter(state))
        },
        error: jqXHR => console.log(jqXHR.responseText)
    });
}

