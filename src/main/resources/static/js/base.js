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
	const { value } = target.dataset;

	const countElement = document.querySelector(".count");
	const priceElement = document.querySelector(".price");
	const totalValue = document.querySelector(".total-value");
	
	let count = +countElement.textContent;
	let price = +priceElement.textContent;

	if (tagName != "BUTTON") return;
	if (count === 1 && +value < 0) return alert("최소 1개이상 ^^");
	count += +value;

	countElement.innerHTML = count;
	totalValue.innerHTML = count * price;
}

function passwordPattern(element) {
	document.querySelector("#password2").pattern = element.value;
}

document.querySelectorAll(".quill").forEach(item => {
	const editors = window.editors || [];
	const quill = new Quill(item, { theme: "snow" });
	const { root } = quill;
	const { name } = item.dataset;
	const quillInputElement = document.querySelector(`[name=${name}]`);
	editors.push(quill);
	window.editors = editors;

	root.onblur = () => {
		if (quillInputElement) quillInputElement.value = quill.root.innerHTML;
	}
})

async function uploadThumbnail(event) {
	const { target } = event;
	const { value } = target;
	const thumbnailElement = document.querySelector(".thumbnail");
	let image = value;
	console.log(image);

	if (value.includes("picsum")) {
		const response = await fetch(value, {
			method: "GET",
			redirect: "manual",
		})
		console.log(response);
		image = response.headers.get("Location");
	}
	thumbnailElement.style.backgroundImage = `url('${image}')`;
	window.temp = thumbnailElement;
}

function calProfit(event) {
	const originPrice = document.querySelector("#originPrice").value;
	const salesPrice = document.querySelector("#salesPrice").value;
	const profitElement = document.querySelector("#profit");

	if (!originPrice || !salesPrice) return;
	
	profitElement.value = (+originPrice - +salesPrice).toLocaleString();
}