//Functions
function setup_page(){
    let example_div = document.getElementById("example-listing-div")
    let new_div = create_view_example()
    example_div.append(new_div)
}

//Example purely for testing
function create_view_example(){
    return create_view_listing_element({"list_id":131})
}

//Creates new div containing the element
function create_view_listing_element(listing){
    let element = document.createElement("div")
    element.setAttribute("class", "m-6 auto")
    element.append(create_button(listing))
    return element
}

//Creates the button
function create_button(listing){
    let button = document.createElement("input")
    button.setAttribute("type", "button")
    button.setAttribute("name", `${listing.list_id}`)
    button.setAttribute("class", "btn btn-secondary view-listing-btn")
    button.setAttribute("value", get_view_listing_button_value())
    button.addEventListener("click", post_listing_page_send)
    return button
}

function get_view_listing_button_value(){
    return `<svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" fill="currentColor" class="bi bi-arrow-right-square" viewBox="0 0 16 16"><path fill-rule="evenodd" d="M15 2a1 1 0 0 0-1-1H2a1 1 0 0 0-1 1v12a1 1 0 0 0 1 1h12a1 1 0 0 0 1-1V2zM0 2a2 2 0 0 1 2-2h12a2 2 0 0 1 2 2v12a2 2 0 0 1-2 2H2a2 2 0 0 1-2-2V2zm4.5 5.5a.5.5 0 0 0 0 1h5.793l-2.147 2.146a.5.5 0 0 0 .708.708l3-3a.5.5 0 0 0 0-.708l-3-3a.5.5 0 1 0-.708.708L10.293 7.5H4.5z"/></svg>View Example Listing`
}

function test_update_span(session){
    let element = document.getElementById("span-1")
    element.innerHTML = `user_id: ${session.user_id} is_mod: ${session.is_mod} is_frozen: ${session.is_frozen} error: ${session.error}`
}

function render_listings(all_listings){
    let listings = document.createElement("div")
    listings.setAttribute("id","list_of_listings")
    let logout_btn = document.getElementById("logout-div")
    document.body.insertBefore(listings,logout_btn)
    for (let listing_json of all_listings) {
        listings.addendChild(listing_json)
    }
    //Populate page
}

function post_listing_page_send(){
    let element_list_id = this.getAttribute("name")
    post_listing_page(element_list_id)
}

//user_id
//list_name
//dungeonName
//max_size

//Async functions
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

async function get_all_listings(){
    try{
        const response = await fetch('/listing/all-listings')
        const results = await response.json()
        if(response.status =! 200){
            const message = `An error occured: Couldn't obtain listings  ${res.status}`
            throw message
        }
        return results
    }catch(e){
        console.log(e)
    }
}

async function post_listing_page(list_id){
    try{
        let formData = new FormData()
        formData.append('list_id', list_id)
        const response = await fetch("/listing", {
            method: 'POST',
            body: formData
        })

        if(!response.ok){
            console.log("Could not load listing.vm from javascript")
        }
    }catch(e){
        console.log(e)
    }
}

setup_page()
get_session()
let all_listings = get_all_listings();
render_listings(all_list)

/**
 * 
const response = await fetch('/account/cancel', {
        headers: {
            'Content-Type': 'application/json'
        },
        method: 'POST',
        body: JSON.stringify({
            'req_id': reqId
        })
    })
 */