//Functions
function test_update_span(session){
    let user_id = document.getElementById("user-id-span")
    let is_mod = document.getElementById("is-mod-span")
    let is_frozen = document.getElementById("is-frozen-span")
    user_id.innerHTML = session.user_id
    is_mod.innerHTML = session.is_mod
    is_frozen.innerHTML = session.is_frozen
}

function update_error_span(msg){
    let error = document.getElementById("error-span")
    error.innerHTML = msg     
}

function remove_entries(){
    let div_location = document.getElementById("list_of_entries")
    div_location.remove()
}

function render_listing_info(listing_obj){
    let list_name_span = document.getElementById("list_name-span")
    let user_id_span = document.getElementById("user_id-span")
    let cur_size_span = document.getElementById("cur_size-span")
    let max_size_span = document.getElementById("max_size-span")
    list_name_span.innerHTML = listing_obj.list_name
    user_id_span.innerHTML = listing_obj.user_id
    cur_size_span.innerHTML = listing_obj.cur_size
    max_size_span.innerHTML = listing_obj.max_size
}

function create_entry_entry_id_element(entry){
    let element = document.createElement("div")
    element.setAttribute("class", "themed-grid-col mt-4")
    element.style.display = "none"
    let p_element = document.createElement("p")
    p_element.setAttribute("class", "entry-entry-id")
    p_element.setAttribute("id", `entry-entry-id-${entry.entry_id}`)
    p_element.innerHTML = `${entry.entry_id}`
    element.append(p_element)

    return element
}

function create_entry_user_id_element(entry){
    let element = document.createElement("div")
    element.setAttribute("class", "col-1 themed-grid-col mt-4")
    element.style.display = "none"
    let p_element = document.createElement("p")
    p_element.setAttribute("class", "entry-user-id")
    p_element.setAttribute("id", `entry-user-id-${entry.user_id}`)
    p_element.innerHTML = `${entry.user_id}`
    element.append(p_element)

    return element
}

function create_username_element(username){
    let element = document.createElement("div")
    element.setAttribute("class", "col-1 themed-grid-col mt-4")
    let p_element = document.createElement("p")
    p_element.setAttribute("class", "username")
    //p_element.setAttribute("id", `username-${user_obj.username}`)
    p_element.innerHTML = `${username}`
    element.append(p_element)

    return element
}

function create_entry_user_role_element(entry){
    let element = document.createElement("div")
    element.setAttribute("class", "col-2 themed-grid-col mt-4")
    let p_element = document.createElement("p")
    p_element.setAttribute("class", "entry-user-role")
    p_element.setAttribute("id", `entry-user-role-${entry.user_role}`)
    p_element.innerHTML = `${entry.user_role}`
    element.append(p_element)

    return element
}

function create_entry_user_note_element(entry){
    let element = document.createElement("div")
    element.setAttribute("class", "col-4 themed-grid-col mt-4")
    let p_element = document.createElement("p")
    p_element.setAttribute("class", "entry-user-note")
    p_element.setAttribute("id", `entry-user-note-${entry.user_id}`)
    p_element.innerHTML = `${entry.user_note}`
    element.append(p_element)

    return element
}

function create_entry_user_status_element(entry){
    let element = document.createElement("div")
    element.setAttribute("class", "col-2 themed-grid-col mt-4")
    let p_element = document.createElement("p")
    p_element.setAttribute("class", "entry-status")
    p_element.setAttribute("id", `entry-status-${entry.entry_id}`)
    p_element.innerHTML = `${entry.status}`
    element.append(p_element)

    return element
}

function create_view_entry_element(listing){
    let element = document.createElement("div")
    element.setAttribute("class", "col-2 themed-grid-col mt-3 auto")
    element.append(create_button(listing))
    return element
}
function create_button(entry){
    let button = document.createElement("input")
    button.setAttribute("id", `select-entry-${entry.entry_id}`)
    button.setAttribute("type", "button")
    button.setAttribute("name", `${entry.entry_id}`)
    button.setAttribute("class", "btn btn-secondary view-entry-btn")
    button.setAttribute("value", "Select Entry")
    button.addEventListener("click", update_entry_id)
    return button
}

function toggle_leave_btn(){
    let leave_btn = document.getElementById("leave-btn")
    if(leave_btn.style.display ==="none")
        leave_btn.style.display = "block";
    else
        leave_btn.style.display = "none";
}

function setup_leave_btn(listing_obj, entry_list){
    let user_id = document.getElementById("user-id-span")
    if (listing_obj.user_id != user_id)
        if(check_user_id_for_entries(user_id, entry_list))
            toggle_leave_btn()
}


function check_user_id_for_entries(user_id, entry_list){
    for(let entry of entry_list.my_list){
        if(entry.user_id == user_id)
            return true
    }
    return false
}

function create_row_div(){
    let row_div = document.createElement("div")
    row_div.setAttribute("class", "row mb-3 entry-row-div")
    return row_div
}

function create_entry_div(){
    let listing_div = document.createElement("div")
    listing_div.setAttribute("id","list_of_entries")
    listing_div.setAttribute("class", "row mb-3")
    return listing_div
}

function setup_btns(){
    let div_location = document.getElementById("join-btn-container")
    let button = document.createElement("input")
    button.setAttribute("type", "button")
    button.setAttribute("name", "create-collapse")
    button.setAttribute("class", "btn btn-secondary create-btn")
    button.setAttribute("value", "Join Listing")
    button.addEventListener("click", hideCollapse)
    div_location.append(button)

    let div_location2 = document.getElementById("update-btn-container")
    let button2 = document.createElement("input")
    button2.setAttribute("type", "button")
    button2.setAttribute("name", "accept-reject-collapse")
    button2.setAttribute("class", "btn btn-secondary update-btn")
    button2.setAttribute("value", "Update Selected Entry")
    button2.addEventListener("click", hideCollapse)
    div_location2.append(button2)

    let accept_btn = document.getElementById("accept-button")
    accept_btn.addEventListener("click", update_entry)
    let reject_btn = document.getElementById("reject-button")
    reject_btn.addEventListener("click", update_entry)
    //let apply_button = document.getElementById("create-entry-btn")
    //apply_button.addEventListener("click", create_entry)
}

function reset_button_class(){
    let accept_btn = document.getElementById("accept-button")
        let reject_btn = document.getElementById("reject-button")
        let kick_btn = document.getElementById("kick-button")
        accept_btn.setAttribute("class", "btn btn-secondary mt-1")
        reject_btn.setAttribute("class", "btn btn-secondary mt-1")
        kick_btn.setAttribute("class", "btn btn-secondary mt-1")
}

function hideCollapse() {
    //Collapse other collapsible
    let collapse_elements = document.getElementsByClassName("collapse")
    for ( element of collapse_elements){
        if (element.classList.contains("show") && element.getAttribute("id") !== this.getAttribute("name")){
            let temp_collapse = new bootstrap.Collapse(element, {
                toggle: true
            })
            temp_collapse.hide();
        }
    }
    //Toggle selected one
    let myCollapse = document.getElementById(this.getAttribute("name"));
    let bsCollapse = new bootstrap.Collapse(myCollapse, {
        toggle: true
    })
    bsCollapse.hide();
}

//function for updating upon selecting an entry
function update_entry_id(){
    let element_entry_id = this.getAttribute("name")
    if(check_valid_user()){
        let accept_btn = document.getElementById("accept-button")
        let reject_btn = document.getElementById("reject-button")
        let kick_btn = document.getElementById("kick-button")
        let accept_input = document.getElementById("accept-entry_id-input")
        let reject_input = document.getElementById("reject-entry_id-input")
        let kick_input = document.getElementById("kick-entry_id-input")
        
        //Set Value
        accept_input.value = element_entry_id
        reject_input.value = element_entry_id
        kick_input.value = element_entry_id

        //Set attributes
        accept_btn.setAttribute("class", "btn btn-success mt-1")
        reject_btn.setAttribute("class", "btn btn-danger mt-1")
        kick_btn.setAttribute("class", "btn btn-primary mt-1")
    }
}

function check_input(){
    //Checks the fields of the collapsible element to make sure it is valid
    //returns true or false
    return true
}

function check_valid_user(){
    let listing_user_id = document.getElementById("user_id-span").innerHTML
    let user_id = document.getElementById("user-id-span").innerHTML
    let is_mod = document.getElementById("is-mod-span").innerHTML
    console.log("check_valid_user: \n\tlisting_user_id: " + listing_user_id
        + "\n\tuser_id: " + user_id
        + "\n\tis_mod" + is_mod)
    return listing_user_id == user_id || is_mod ==="true"
}

//Async Functions
async function reload_page(list_id){
    let listing_obj = await get_particular_listing(list_id)
    await render_listing_info(listing_obj)
    remove_entries()
    reset_button_class()
    let list_of_entry = await get_all_entries_of_listing(list_id)
    await render_entry_info(list_of_entry)
    render_user_info()
}
async function render_entry_info(list_of_entry){
    let entry_div = document.getElementById("entry-info-div")
    let entries = create_entry_div()
    for(let entry_json of list_of_entry.my_list){
        let row_div = create_row_div(entry_json)
        let login_list = await get_all_username()
        let username =""
        for(let login of login_list.my_list){
            if(login.userId == entry_json.user_id)
                username = login.username
        }
        entries.append(row_div)
        list_of_elements = []
        list_of_elements.push(create_entry_user_id_element(entry_json))
        list_of_elements.push(create_username_element(username))
        list_of_elements.push(create_entry_user_role_element(entry_json))
        list_of_elements.push(create_entry_user_note_element(entry_json))
        list_of_elements.push(create_entry_user_status_element(entry_json))
        list_of_elements.push(create_view_entry_element(entry_json))
        //This element is hidden and must be last for render_user_info()
        list_of_elements.push(create_entry_entry_id_element(entry_json))

        for(element of list_of_elements){
            row_div.append(element)
        }
    }
    entry_div.append(entries)
}

async function render_user_info(){
    let user_id = document.getElementById("user-id-span").innerHTML
    let post_entry_id_element = document.getElementById("leave-entry-input")
    let post_user_id_element = document.getElementById("leave-user-input")
    let leave_btn_element = document.getElementById("leave-btn")
    let my_list_rows = await document.getElementsByClassName("entry-row-div")
    let hasJoined = document.getElementById("hasJoined")

    for(let entry_row of my_list_rows){
        let entry_status = entry_row.children[4].firstElementChild.innerHTML
        let entry_entry_id = entry_row.lastElementChild.firstElementChild.innerHTML
        let entry_user_id = entry_row.firstElementChild.firstElementChild.innerHTML
        console.log("entryID: " + entry_entry_id + " entryUID: " + entry_user_id)
        if (entry_user_id == user_id && entry_status === "Accepted"){
            hasJoined.innerHTML = "true"
            post_entry_id_element.setAttribute("value", entry_entry_id)
            post_user_id_element.setAttribute("value", user_id)
            leave_btn_element.setAttribute("class", "btn btn-danger mt-2")
            break
        }
        else{
            hasJoined.innerHTML = "false"
        }
    }
}

async function get_particular_listing(list_id){
    try{
        const response = await fetch(`/listing/manage/${list_id}`)
        const pReq = await response.json()
        if (response.status != 200){
            const message = `Couldn't obtain listing! An error occured: ${res.status}`
            throw message
        }
        return pReq
    }catch(err){
        update_error_span(err)
        console.log(err)
    }
}

async function get_all_entries_of_listing(list_id){
    try{
        const response = await fetch(`/listing/manage/${list_id}/entry`)
        const pReq = await response.json()
        if (response.status != 200){
            const message = `Couldn't obtain entries! An error occured: ${res.status}`
            throw message
        }
        return pReq
    }catch(err){
        update_error_span(err)
        console.log(err)
    }
}

async function get_session() {
    try{
        const response = await fetch('/session')
        const pReq = await response.json()
        if (response.status != 200){
            const message = `Couldn't obtain session! An error occured: ${res.status}`
            throw message
        }
        test_update_span(pReq)
        return pReq
    }catch(err){
        update_error_span(err)
        console.log(err)
    }
}

async function create_entry(list_id){
    let hasJoined = document.getElementById("hasJoined").innerHTML
    if(hasJoined === "false"){
        remove_entries()
        let list_of_entry = await get_all_entries_of_listing(list_id)
        await render_entry_info(list_of_entry)
        //Check input
        console.log("create_entry: " + list_id)
        let roles = document.getElementById("entry-role")
        let user_role = roles.options[roles.selectedIndex].innerText
        let user_id = document.getElementById("user-id-span").innerHTML
        let user_note = document.getElementById("desc-input").value
        let formData = new FormData()
        formData.append('list_id', list_id)
        formData.append('user_id', user_id)
        formData.append('user_role', user_role)
        formData.append('user_note', user_note)
        
                

        let response = await fetch(`/listing/manage/${list_id}/entry/create`, {
            method: 'POST',
            body: formData
        })
        
        if(!response.ok){
            update_error_span("Could not create entry from javascript")
            console.log("Could not create entry from javascript")
        }
        

        await reload_page(list_id)
    }
}

 async function update_entry(){
    // /listing/manage/$list_id/entry/update POST
    let list_id_input = 0, entry_id_input = 0, status_input = 0
    let list_id = document.getElementById("listing-info-span").innerHTML
    //Check if user is listing leader or moderator
    if(check_valid_user()){
        if (this.getAttribute("id") === "accept-button"){
            list_id_input = document.getElementById("accept-list_id-input").value
            entry_id_input = document.getElementById("accept-entry_id-input").value
            status_input = document.getElementById("accept-status-input").value
        }else if(this.getAttribute("id") == "reject-button"){
            list_id_input = document.getElementById("reject-list_id-input").value
            entry_id_input = document.getElementById("reject-entry_id-input").value
            status_input = document.getElementById("reject-status-input").value
        }
        
        console.log("update_entry=> \n\tlist_id_input: " + list_id_input 
            + "\n\tentry_id_input: " + entry_id_input 
            + "\n\tstatus_input: " + status_input)

        let formData = new FormData()
        formData.append('list_id', list_id_input)
        formData.append('entry_id', entry_id_input)
        formData.append('status', status_input)

        let response = await fetch(`/listing/manage/${list_id}/entry/update`, {
            method: 'POST',
            body: formData
        })

        if(!response.ok){
            console.log("Could not update entry from javascript")
            update_error_span("Could not update entry from javascript")
        }        
    }

    
    await reload_page(list_id)
}

async function kick_entry(){
    // /listing/manage/$list_id/entry/delete POST
    let list_id_input = 0, entry_id_input = 0, user_id_input = 0
    let list_id = document.getElementById("listing-info-span").innerHTML
    //Check if user is listing leader or moderator
    if(check_valid_user()){
        list_id_input = document.getElementById("kick-list_id-input").value
        entry_id_input = document.getElementById("kick-entry_id-input").value
        user_id_input = document.getElementById("kick-user_id-input").value
        
        console.log("kick_entry=> \n\tlist_id_input:" + list_id_input 
            + "\n\tentry_id_input: " + entry_id_input 
            + "\n\tuser_id_input: " + user_id_input)

        let formData = new FormData()
        formData.append('list_id', list_id_input)
        formData.append('entry_id', entry_id_input)
        formData.append('user_id', user_id_input)

        let response = await fetch(`/listing/manage/${list_id}/entry/delete`, {
            method: 'POST',
            body: formData
        })

        if(!response.ok){
            console.log("Could not delete entry from javascript")
            update_error_span("Could not delete entry from javascript")
        }        
    }

    await reload_page(list_id)
}

async function get_all_username(user_id){
    try{
        const response = await fetch("/username", {method: 'GET'})
        const pReq = await response.json()
        if(!response.ok){
            const message = `get usernames failed`
            throw message
        }
        return pReq
    }catch(err){
        update_error_span(err)
    }
}

async function render_full_page(){
    //Grab list_id from page
    let listing_info_span = document.getElementById("listing-info-span")
    let list_id = listing_info_span.innerHTML
    //code to fetch entries and listing obj
    let listing_obj = await get_particular_listing(list_id)
    let list_of_entry = await get_all_entries_of_listing(list_id)
    let session_obj = await get_session()
    
    setup_btns()
    //setup_leave_btn(listing_obj, list_of_entry)
    render_listing_info(listing_obj)
    await render_entry_info(list_of_entry)
    render_user_info()
}
//get_all_entries_of_listing(list_id)
render_full_page()