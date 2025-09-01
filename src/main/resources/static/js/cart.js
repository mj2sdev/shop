const { createApp } = Vue;
createApp({
	setup() {
		const csrfHeader = document.querySelector("meta[name=_csrf_header]").content;
		const csrf = document.querySelector("meta[name=_csrf]").content;

		async function dropProductInCart() {
			if (!confirm("상품을 장바구니에서 삭제하시겠습니까?")) return;

			const itemId = event.currentTarget.dataset.id;
			const response = await fetch(`/product/cart/delete/${itemId}`, {
				method: "POST",
				headers: {
					"Content-Type": "application/json",
					[csrfHeader]: csrf
				},
				body: JSON.stringify({ id: itemId })
			})

			const result = await response.json();
			if (result.result) {
				alert(result.message);
				location.reload();
			}
		}

		async function changeQuantity() {
			const value = event.currentTarget.value;
			const itemId = event.currentTarget.dataset.id;
			const response = await fetch("/product/cart/change", {
				method: "post",
				body: JSON.stringify({ id: itemId, quantity: value }),
				headers: {
					"Content-Type": "application/json",
					[csrfHeader]: csrf,
				}
			});

			const result = await response.json();
			console.log(result);
		}

		return {
			dropProductInCart, changeQuantity
		}
	},
	
}).mount("body");