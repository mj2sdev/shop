M.AutoInit();

const images = document.querySelectorAll("img");

for (image of images) {
	image.onerror = () => {
		image.src = "https://search-bank.net/contents/images/3613b9aef50355d5b9189c40a7eab681.jpg";
		const next = image.nextElementSibling;
		if (next) next.innerHTML = "이미지 없음";
	};
	if (image.complete && image.naturalWidth === 0) image.onerror();
}

function counter(event) {
	const { target } = event;
	const { tagName } = target;
	const closest = target.closest("div");
	const { value } = closest.dataset;

	const countElement = document.querySelector("#count");
	const priceElement = document.querySelector("#price");
	const totalElement = document.querySelector("#totalPrice");
	
	const count = +countElement.value + +value;
	const price = +priceElement.textContent;
	const totalPrice = (count * price).toLocaleString();

	if (tagName != "I" && tagName != "BUTTON") return;
	if (count < 1) return doToast("최소 1개 이상^^");

	countElement.value = count;
	totalElement.innerHTML = totalPrice;
}

function passwordPattern(element) {
	document.querySelector("#password2").pattern = element.value;
}

document.querySelectorAll(".quill").forEach(item => {
	const tollbarOptions = [
		["bold", "italic", "underline", "strike"],
		["blockquote", "code-block"],
		[{ header: 1 }, { header: 2 }],
		[{ list: "ordered" }, { list: "bullet" }],
		[{ script: "sub" }, { script: "super" }],
		[{ indent: "-1" }, { indent: "+1" }],
		[{ direction: "rtl" }],
		[{ size: ["small", false, "large", "huge"] }],
		[{ header: [1, 2, 3, 4, 5, 6, false] }],
		[{ color: [] }, { background: [] }],
		[{ font: [] }],
		[{ align: [] }],
		["clean"],
		["link", "image", "video"],
	]
	const editors = window.editors || [];
	const quill = new Quill(item, { theme: "snow", modules: { toolbar: tollbarOptions } });
	const { root } = quill;
	const { name } = item.dataset;
	const quillInputElement = document.querySelector(`[name=${name}]`);
	editors.push(quill);
	window.editors = editors;

	root.onblur = () => {
		if (quillInputElement) quillInputElement.value = quill.root.innerHTML;
	}
})

function previewImage(fileElement, viewElementSelector) {
	const file = fileElement.files[0];
	const viewElement = document.querySelector(viewElementSelector);
	const reader = new FileReader();
	reader.onload = () => {
		viewElement.setAttribute("src", reader.result);
		// viewElement.style.backgroundImage = `url('${ reader.result }')`
		viewElement.hidden = false;
	}

	if (!file) return viewElement.hidden = true;
	reader.readAsDataURL(file);
	
}

function calProfit(event) {
	const originPrice = document.querySelector("#originPrice").value;
	const salesPrice = document.querySelector("#salesPrice").value;
	const profitElement = document.querySelector("#profit");

	if (!originPrice || !salesPrice) return;
	
	profitElement.value = (+salesPrice - +originPrice).toLocaleString();
}

function doToast(message) {
	M.toast({ html: message, classes: "rounded" });
}

document.querySelectorAll("[data-toast]").forEach(item => {
	const { toast } = item.dataset;
	const toastEvent = () => doToast(toast);
	item.onclick = toastEvent;
});

function getCsrfToken() {
	const token = document.querySelector("meta[name=_csrf]").content;
	const headerName = document.querySelector("meta[name=_csrf_header]").content;
	return { token, headerName };
}

async function addCart() {
	if (!confirm("상품을 장바구니에 추가하시겠습니까?")) return;

	const productId = +document.querySelector("#id").innerHTML;
	const quantity = +document.querySelector("#count").value;
	const csrf = getCsrfToken();
	
	const response = await fetch("/product/cart/add", {
		method: "POST",
		headers: {
			"Content-Type": "application/json",
			[csrf.headerName]: csrf.token,
		},
		body: JSON.stringify({ productId, quantity }),
	})

	const result = await response.json();
	if (result.result) {
		if (!confirm(result.message + "\n장바구니로 바로 가시겠습니까?")) return;
		location.href = "/product/cart";
	}
}

function deleteProductProcess(event) {
	const formElement = event.target;
	const { ids } = formElement;
	if (!ids) return event.preventDefault();

	const value = [...ids]
		.map(id => id.checked ? id.value : '')
		.reduce((acc, value) => acc + value);
	
	const firstId = ids[0];
	console.table(value)
	if (!value) {
		firstId.setAttribute("required", "");
		formElement.reportValidity();
		event.preventDefault();
	} else {
		firstId.removeAttribute("required");
	}
}

const message = document.querySelector("meta[name=message]").content;
if (message) {
	M.toast({
		html: message,
		classes: "rounded",
	})
}