//Functions
function test_update_span(session){
    let user_id = document.getElementById("user-id-span")
    let is_mod = document.getElementById("is-mod-span")
    let error = document.getElementById("error-span")
    user_id.innerHTML = session.user_id
    is_mod.innerHTML = session.is_mod
    error.innerHTML = session.error
}

function remove_entries(){
    let div_location = document.getElementById("list_of_entries")
    div_location.remove()
}

function render_listing_info(listing_obj){
    let listing_desc = document.getElementById("listing-desc")
    let list_name_p = document.createElement("p")
    let list_dungeon_p = document.createElement("p")
    let list_leader_p = document.createElement("p")
    let list_group_size_p = document.createElement("p")
    list_name_p.setAttribute("id", `list_name_p`)
    list_dungeon_p.setAttribute("id", `list_dungeon_p`)
    list_leader_p.setAttribute("id", `list_leader_p`)
    list_name_p.innerHTML = `${listing_obj.list_name}`
    list_dungeon_p.innerHTML = `${listing_obj.dungeonName}`
    list_leader_p.innerHTML = `Leader: ${listing_obj.user_id} - `
    list_leader_p.innerHTML += `Size: ${listing_obj.cur_size}/${listing_obj.max_size}`
    listing_desc.append(list_name_p)
    listing_desc.append(list_dungeon_p)
    listing_desc.append(list_leader_p)
}

function create_entry_user_id_element(entry){
    let element = document.createElement("div")
    element.setAttribute("class", "col-1 themed-grid-col mt-4")
    let p_element = document.createElement("p")
    p_element.setAttribute("class", "entry-user-id")
    p_element.setAttribute("id", `entry-user-id-${entry.user_id}`)
    p_element.innerHTML = `${entry.user_id}`
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

    //let apply_button = document.getElementById("create-entry-btn")
    //apply_button.addEventListener("click", create_entry)
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

function update_list_id(){
    let element_list_id = this.getAttribute("name")

    let display_form = document.getElementById("display-list_id")
    display_form.setAttribute("value", element_list_id)
    let view_btn = document.getElementById("view-btn")
    view_btn.setAttribute("class", "btn btn-success")

    let remove_form = document.getElementById("remove-list_id")
    remove_form.setAttribute("value", element_list_id)
    let delete_btn = document.getElementById("delete-btn")
    delete_btn.setAttribute("class", "btn btn-danger")
}

function check_input(){
    //Checks the fields of the collapsible element to make sure it is valid
    //returns true or false
    return true
}

//Async Functions
async function render_entry_info(list_of_entry){
    let entry_div = document.getElementById("entry-info-div")
    let entries = create_entry_div()
    for(let entry_json of list_of_entry.my_list){
        let row_div = create_row_div(entry_json)
        entries.append(row_div)
        list_of_elements = []
        list_of_elements.push(create_entry_user_id_element(entry_json))
        list_of_elements.push(create_entry_user_role_element(entry_json))
        list_of_elements.push(create_entry_user_note_element(entry_json))
        list_of_elements.push(create_entry_user_status_element(entry_json))

        for(element of list_of_elements){
            row_div.append(element)
        }
    }
    entry_div.append(entries)
}

async function render_user_info(){
    let user_id = document.getElementById("user-id-span").innerHTML
    let post_user_id_element = document.getElementById("leave-input")
    let leave_btn_element = document.getElementById("leave-btn")
    let my_list_rows = document.getElementsByClassName("entry-row-div")
    let my_list = []
    let changed_element = 0
    for(let entry_row of my_list_rows){
        my_list.push(entry_row.firstElementChild.firstElementChild.innerHTML)
    }
    for (let user of my_list){
        if (user == user_id){
            changed_element = user
            post_user_id_element.setAttribute("value", user_id)
            leave_btn_element.setAttribute("class", "btn btn-danger mt-2")
            break
        }
    }
    //console.log("User_id is changed to: " + changed_element.getAttribute("value"))
}

async function get_particular_listing(list_id){
    try{
        const response = await fetch(`/listing/manage/${list_id}`)
        const pReq = await response.json()
        if (response.status != 200){
            const message = `Couldn't obtain entries! An error occured: ${res.status}`
            throw message
        }
        return pReq
    }catch(err){
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
        console.log(err)
    }
}

async function get_session() {
    try{
        const response = await fetch('/session')
        const pReq = await response.json()
        if (response.status != 200){
            const message = `Couldn't obtain requests! An error occured: ${res.status}`
            throw message
        }
        test_update_span(pReq)
        return pReq
    }catch(err){
        console.log(err)
    }
}

async function create_entry(list_id){
    //Check input
    console.log(list_id)
    let roles = document.getElementById("entry-role")
    let user_role = roles.options[roles.selectedIndex].innerText
    let user_id = document.getElementById("user-id-span").innerHTML
    let user_note = document.getElementById("desc-input").value
    let formData = new FormData()
    formData.append('list_id', list_id)
    formData.append('user_id', user_id)
    formData.append('user_role', user_role)
    formData.append('user_note', user_note)
    
            

    const response = await fetch(`/listing/manage/${list_id}/entry/create`, {
        method: 'POST',
        body: formData
    })
    
    
    remove_entries()
    let list_of_entry = await get_all_entries_of_listing(list_id)
    render_entry_info(list_of_entry)
    render_user_info()
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
    setup_leave_btn(listing_obj, list_of_entry)
    render_listing_info(listing_obj)
    render_entry_info(list_of_entry)
    render_user_info()
}
//get_all_entries_of_listing(list_id)
render_full_page()