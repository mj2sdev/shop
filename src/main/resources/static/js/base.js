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