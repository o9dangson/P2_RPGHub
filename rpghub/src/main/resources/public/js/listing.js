//Functions
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

async function render_entry_info(list_of_entry){
    console.log("render_entry_info: " + list_of_entry)
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

function toggle_leave_btn(){
    let leave_btn = document.getElementById("leave-btn")
    if(leave_btn.style.display ==="none")
        leave_btn.style.display = "block";
    else
        leave_btn.style.display = "none";
}

function setup_leave_btn(listing_obj, session_obj, entry_list){
    if (listing_obj.user_id != session_obj.user_id)
        if(check_user_id_for_entries(session_obj.user_id, entry_list))
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
    row_div.setAttribute("class", "row mb-3 listing-row-div")
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

    let apply_button = document.getElementById("create-entry-btn")
    apply_button.addEventListener("click", create_entry)
}

function hideCollapse() {
    var myCollapse = document.getElementById(this.getAttribute("name"));
    var bsCollapse = new bootstrap.Collapse(myCollapse, {
        toggle: true
    })
    bsCollapse.hide();
}

function check_input(){
    //Checks the fields of the collapsible element to make sure it is valid
    //returns true or false
    return true
}

//Async Functions
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
        return pReq
    }catch(err){
        console.log(err)
    }
}

async function create_entry(list_id){
    //Check input
    if(check_input()){
        const response = await fetch(`/listing/manage/${list_id}/entry/create`)
        const new_entry = await response
    }else{

    }
}

async function update_entry(){

}

async function delete_entry(){

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
    setup_leave_btn(listing_obj, session_obj, list_of_entry)
    render_listing_info(listing_obj)
    render_entry_info(list_of_entry)
}

render_full_page()