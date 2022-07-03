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

function create_listing_div(){
    let listing_div = document.createElement("div")
    listing_div.setAttribute("id","list_of_listings")
    listing_div.setAttribute("class", "row mb-3")
    return listing_div
}

function create_row_div(){
    let row_div = document.createElement("div")
    row_div.setAttribute("class", "row mb-3 listing-row-div")
    return row_div
}

function create_list_id_element(listing){
    let element = document.createElement("div")
    element.setAttribute("class", "themed-grid-col")
    element.style.display = "none"
    let p_element = document.createElement("p")
    p_element.setAttribute("class", "list_id")
    p_element.setAttribute("id", `list-id-${listing.list_id}`)
    p_element.innerHTML = `${listing.list_id}`
    element.append(p_element)

    return element
}

function create_user_id_element(listing){
    let element = document.createElement("div")
    element.setAttribute("class", "col-1 themed-grid-col mt-4")
    element.style.display = "none"
    let p_element = document.createElement("p")
    p_element.setAttribute("class", "user_id")
    p_element.setAttribute("id", `user-id-${listing.user_id}`)
    p_element.innerHTML = `${listing.user_id}`
    element.append(p_element)

    return element
}

function create_username_element(listing, username_list){
    let element = document.createElement("div")
    element.setAttribute("class", "col-1 themed-grid-col mt-4")
    let p_element = document.createElement("p")
    p_element.setAttribute("class", "username")
    //p_element.setAttribute("id", `username-${user_obj.username}`)
    let username = "default"
    for(let user of username_list){
        if (user.userId == listing.user_id)
            username = user.username
    }
    p_element.innerHTML = `${username}`
    element.append(p_element)

    return element
}

function create_list_name_element(listing){
    let element = document.createElement("div")
    element.setAttribute("class", "col-3 themed-grid-col mt-4")
    let p_element = document.createElement("p")
    p_element.setAttribute("class", "list_name")
    p_element.setAttribute("id", `list-name-${listing.list_name}`)
    p_element.innerHTML = `${listing.list_name}`
    element.append(p_element)

    return element
}
function create_dungeonName_element(listing){
    let element = document.createElement("div")
    element.setAttribute("class", "col-4 themed-grid-col mt-4")
    let p_element = document.createElement("p")
    p_element.setAttribute("class", "dungeonName")
    p_element.setAttribute("id", `dungeonName-${listing.dungeonName}`)
    p_element.innerHTML = `${listing.dungeonName}`
    element.append(p_element)

    return element
}
function create_max_size_element(listing){
    let element = document.createElement("div")
    element.setAttribute("class", "col-1 themed-grid-col mt-4")
    let p_element = document.createElement("p")
    p_element.setAttribute("class", "max_size")
    p_element.setAttribute("id", `max-size-${listing.max_size}`)
    p_element.innerHTML = `${listing.max_size}`
    element.append(p_element)

    return element
}
function create_cur_size_element(listing){
    let element = document.createElement("div")
    element.setAttribute("class", "col-1 themed-grid-col mt-4")
    let p_element = document.createElement("p")
    p_element.setAttribute("class", "cur_size")
    p_element.setAttribute("id", `cur-size-${listing.cur_size}`)
    p_element.innerHTML = `${listing.cur_size}`
    element.append(p_element)

    return element
}
function create_view_listing_element(listing){
    let element = document.createElement("div")
    element.setAttribute("class", "col-2 themed-grid-col mt-3 auto")
    element.append(create_button(listing))
    return element
}
function create_button(listing){
    let button = document.createElement("input")
    button.setAttribute("id", `select-listing-${listing.list_id}`)
    button.setAttribute("type", "button")
    button.setAttribute("name", `${listing.list_id}`)
    button.setAttribute("class", "btn btn-secondary view-listing-btn")
    button.setAttribute("value", "Select Listing")
    button.addEventListener("click", update_list_id)
    return button
}

function remove_listings(){
    let div_location = document.getElementById("list_of_listings")
    div_location.remove()
}

function hideCollapse() {
    let collapse_elements = document.getElementsByClassName("collapse")
    for ( element of collapse_elements){
        if (element.classList.contains("show") && element.getAttribute("id") !== this.getAttribute("name")){
            let temp_collapse = new bootstrap.Collapse(element, {
                toggle: true
            })
            temp_collapse.hide();
        }
    }
    let myCollapse = document.getElementById(this.getAttribute("name"));
    let bsCollapse = new bootstrap.Collapse(myCollapse, {
        toggle: true
    })
    bsCollapse.hide();
}

function update_list_id(){
    let element_user_id = this.parentElement.parentElement.firstElementChild.nextSibling.firstElementChild.innerHTML
    let element_list_id = this.getAttribute("name")
    let user_id = document.getElementById("user-id-span").innerHTML

    let display_user_id = document.getElementById("display-user_id")
    let display_list_id = document.getElementById("display-list_id")
    let remove_user_user_id = document.getElementById("remove-user-user_id")
    let remove_list_user_id = document.getElementById("remove-list-user_id")
    let remove_list_id = document.getElementById("remove-list_id")
    let view_btn = document.getElementById("view-btn")
    let delete_btn = document.getElementById("delete-btn")
    
    display_user_id.setAttribute("value", element_user_id)
    display_list_id.setAttribute("value", element_list_id)
    remove_user_user_id.setAttribute("value", user_id)
    remove_list_user_id.setAttribute("value", element_user_id)
    remove_list_id.setAttribute("value", element_list_id)

    view_btn.setAttribute("class", "btn btn-success")
    delete_btn.setAttribute("class", "btn btn-danger")
}

function clear_filters(){
    remove_listings()
    get_all_listings()
}

function html_to_json(html_list){
    let json_string = "";
    json_string+="{\"my_list\":[";
    for(let html of html_list){
        json_string+=`{\"list_id\": ${html.children[0].innerText},`;
        json_string+=`\"user_id\": ${html.children[1].innerText},`;
        json_string+=`\"username\": "${html.children[2].innerText}",`;
        json_string+=`\"list_name\": "${html.children[3].innerText}",`;
        json_string+=`\"dungeonName\": "${html.children[4].innerText}",`;
        json_string+=`\"max_size\": ${html.children[5].innerText},`;
        json_string+=`\"cur_size\": ${html.children[6].innerText}}`;
        if(html_list.indexOf(html)<html_list.length-1){
            json_string+=",";
        }
    }
    json_string+="]}";
    //console.log(json_string)
    let json_obj = JSON.parse(json_string);
    //console.log(json_obj)
    return json_obj;
}

function update_moderator_list(username_list){
    let dropdown_ele = document.getElementById("account-category")
    dropdown_ele.options.length = 0
    for(let user of username_list){
        dropdown_ele.options[dropdown_ele.options.length] = new Option(`${user.username}`, `${user.userId}`)
    }
}

//Async functions
async function render_listings(all_listings, username_list){
    //Get location to append to
    let div_location = document.getElementById("listing-display-div")
    //Create new div to append to this location
    let listing_div = create_listing_div()
    //Create div for each row (listing)
    for (let listing_json of all_listings.my_list){
        let row_div = create_row_div(listing_json)
        listing_div.append(row_div)
        const list_of_elements = []
        //Create element(s) for containing listing information
        list_of_elements.push(create_list_id_element(listing_json))
        list_of_elements.push(create_user_id_element(listing_json))
        list_of_elements.push(create_username_element(listing_json, username_list))
        list_of_elements.push(create_list_name_element(listing_json))
        list_of_elements.push(create_dungeonName_element(listing_json))
        list_of_elements.push(create_max_size_element(listing_json))
        list_of_elements.push(create_cur_size_element(listing_json))
        //Create view_listing_btn
        list_of_elements.push(create_view_listing_element(listing_json))
        //Append everything to new row div
        for(element of list_of_elements){
            row_div.append(element)
        }
    }
    //Append new div to location
    div_location.append(listing_div);
}

async function setup_btns(){
    let div_location = document.getElementById("filter-btn-container")
    let button = document.createElement("input")
    button.setAttribute("type", "button")
    button.setAttribute("name", "filter-collapse")
    button.setAttribute("class", "btn btn-secondary filter-btn")
    button.setAttribute("value", "Filter Listings")
    button.addEventListener("click", hideCollapse)
    div_location.append(button)

    let div_location2 = document.getElementById("create-btn-container")
    let button2 = document.createElement("input")
    button2.setAttribute("type", "button")
    button2.setAttribute("name", "create-collapse")
    button2.setAttribute("class", "btn btn-secondary create-btn")
    button2.setAttribute("value", "Create Listing")
    button2.addEventListener("click", hideCollapse)
    div_location2.append(button2)

    let is_mod = document.getElementById("is-mod-span").innerHTML
    let div_location3 = document.getElementById("mod-btn-container")
    let button3 = document.createElement("input")
    button3.setAttribute("type", "button")
    button3.setAttribute("name", "mod-collapse")
    if(is_mod !== "true"){
        console.log(is_mod)
        console.log("You are not a mod")
        button3.style.display = "none"
    }
    button3.setAttribute("class", "btn btn-primary mod-btn")
    button3.setAttribute("value", "Mod Menu")
    button3.addEventListener("click", hideCollapse)
    div_location3.append(button3)

    
    let button4 = document.getElementById("mod-freeze-btn")
    button4.addEventListener("click", manipulate_account)
    let button5 = document.getElementById("mod-unfreeze-btn")
    button5.addEventListener("click", manipulate_account)
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
    }catch(err){
        console.log(err)
    }
}

async function get_all_username(){
    try{
        const response = await fetch("/username", {method: 'GET'})
        const pReq = await response.json()
        if(!response.ok){
            const message = `get usernames failed`
            throw message
        }
        //Update moderator list
        update_moderator_list(pReq.my_list)
        return pReq
    }catch(err){
        update_error_span(err)
    }
}

async function manipulate_account(){
    let select_ele = document.getElementById("account-category")
    let select_option = select_ele.options[select_ele.options.selectedIndex].value
    let formData = new FormData()
    let is_frozen = ""
    if(this.getAttribute("id") === "mod-freeze-btn")
        is_frozen = "true"
    else if (this.getAttribute("id") === "mod-unfreeze-btn")
        is_frozen = "false"
    formData.append('user_id', select_option)
    formData.append('is_frozen', is_frozen)
            

    const response = await fetch("/account/moderator", {
        method: 'POST',
        body: formData
    })
}

async function create_new_listing(){
    let list_name = document.getElementById("create-list-name")
    let dungeonName = document.getElementById("create-dungeon-name")
    let max_size = document.getElementById("create-max-size")
    let user_id = document.getElementById("user-id-span")
    console.log(user_id)
    let formData = new FormData()
    formData.append('user_id', user_id.innerHTML)
    formData.append('list_name', list_name.value)
    formData.append('dungeonName', dungeonName.value)
    formData.append('max_size' , max_size.value)
            

    const response = await fetch("/listing/create", {
        method: 'POST',
        body: formData
    })
    remove_listings()
    await get_all_listings()
}

async function get_all_listings(){
    try{
        const response = await fetch('/listing/all-listings')
        const results = await response.json()
        if(response.status =! 200){
            const message = `An error occured: Couldn't obtain listings  ${res.status}`
            throw message
        }
        let username_list = await get_all_username()
        await render_listings(results, username_list.my_list)
    }catch(e){
        console.log(e)
    }
}

async function filter_listing() {
    remove_listings()
    await get_all_listings()
    let categories = document.getElementById('filter-category')
    let category = categories.options[categories.selectedIndex].value
    let filtered_elements = document.getElementsByClassName(category)
    let specific_filter_val = document.getElementById("specific-filter-input").value

    const filtered_html = [];
    for(listing of filtered_elements){
        if(listing.innerText === specific_filter_val){
            console.log("listing matches filter:\n" + listing);
            let cat_element = listing.parentElement
            let listing_row_html = cat_element.parentElement
            filtered_html.push(listing_row_html)
        }
    }
    let filtered_json = html_to_json(filtered_html)
    remove_listings()
    let username_list = await get_all_username()
    await render_listings(filtered_json, username_list.my_list)
    
}

async function setup(){
    await get_session()
    await get_all_listings()
    await setup_btns()
}
console.log("this is working")

setup()
