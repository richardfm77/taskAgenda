window.addEventListener('DOMContentLoaded', event => {
    new Vue({
        el: '#app',
        vuetify: new Vuetify(),
        data: () => ({
            search: '',
            dialog: false,
            dialogDelete: false,
            headers: [
                {
                    text: 'Name',
                    align: 'start',
                    sortable: true,
                    value: 'name',
                },
                { text: 'Date', value: 'date' },
                { text: 'Hour', value: 'hour' },
                { text: 'Comments', value: 'comments' },
                { text: 'Status', value: 'status'},
                { text: 'Actions', value: 'actions', sortable: false },
            ],
            desserts: [],
            editedIndex: -1,
            editedItem: {
                iduser: null,
                name: '',
                date: '',
                hour: '',
                comments: '',
                status: '',
                id: null
            },
            defaultItem: {
                iduser: null,
                name: '',
                date: '',
                hour: '',
                comments: '',
                status: '',
                id: null
            },
        }),

        computed: {
            formTitle() {
                return this.editedIndex === -1 ? 'New Task' : 'Edit Task';
            },
        },

        watch: {
            dialog(val) {
                val || this.close()
            },
            dialogDelete(val) {
                val || this.closeDelete()
            },
        },

        created() {
            this.initialize()
        },

        methods: {
            initialize() {
                setHeaderView();
                getActivities().then(activities => {
                    this.desserts = activities;
                });
            },

            editItem(item) {
                this.editedIndex = this.desserts.indexOf(item);
                this.editedItem = Object.assign({}, item);
                this.dialog = true;
            },

            deleteItem(item) {
                this.editedIndex = this.desserts.indexOf(item);
                this.editedItem = Object.assign({}, item);
                this.dialogDelete = true;
            },

            deleteItemConfirm() {
                this.desserts.splice(this.editedIndex, 1);
                deleteContact(this.editedItem.id);
                this.closeDelete();
            },

            close() {
                this.dialog = false
                this.$nextTick(() => {
                    this.editedItem = Object.assign({}, this.defaultItem);
                    this.editedIndex = -1;
                })
            },

            closeDelete() {
                this.dialogDelete = false
                this.$nextTick(() => {
                    this.editedItem = Object.assign({}, this.defaultItem);
                    this.editedIndex = -1;
                })
            },

            save() {
                postActivity(
                    this.editedItem.name,
                    this.editedItem.date,
                    this.editedItem.hourIn,
                    this.editedItem.hourEnd,
                    this.editedItem.comments,
                    this.editedItem.status,
                    this.editedItem.id
                );

                this.close();

                location.reload();
            },
        },
    })
});

function setHeaderView() {

    let nameTitle = document.getElementById("nameTitle");
    let emailTitle = document.getElementById("emailTitle");

    nameTitle.textContent = localStorage.name;
    emailTitle.textContent = localStorage.email;

}

//-------------------------------
/**
 * Comunication with backend.
 */

async function getActivities() {
    const request = await fetch('/activity/get/' + localStorage.id, {
        method: 'GET',
        headers: getHeaders()
    });

    const contacts = await request.json();
    return contacts;
}

function getHeaders() {
    return {
        'Accept': 'application/json',
        'Content-Type': 'application/json',
        'Authorization': localStorage.token
    };
}

async function deleteContact(id) {
    const request = await fetch('/activity/delete/' + id, {
        method: 'DELETE',
        headers: getHeaders()
    });
}

async function postActivity(name, date, hourIn, hourEnd, comments, status, id) {

    let activity = {
        iduser: localStorage.id,
        name: name,
        date: date,
        hour: hourIn + "-" + hourEnd,
        comments: comments,
        status: status,
        id: id
    };

    if (activity.name === "") {
        alert("Name is required");
        return;
    }

    const request = await fetch('/activity/post', {
        method: 'POST',
        headers: getHeaders(),
        body: JSON.stringify(activity)
    });
}