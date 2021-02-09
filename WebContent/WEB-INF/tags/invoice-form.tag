<%@ tag language="java" pageEncoding="UTF-8"%>

<form method="post">
	<div class="container">
		<h3>Invoice & Client Informaiton</h3>
		<table>
			<tr>
				<th>Invoice date</th>
				<td name="invoiceDate" id="invoiceDate">
				</td>
				<th>Number</th>
				<td name="invoiceId" id="invoiceId">
				</td>
				<td class="no-border">
					<input type="checkbox" name="paid" id="paid">
					<label for="paid">Paid</label>
				</td>
			</tr>
		</table>
	</div>

	<div class="container">
		<table>
			<tr>
				<th>NIF</th>
				<td name="nif" id="nif" contenteditable>
				</td>
				<th>Name</th>
				<td colspan="3" name="clientName" id="clientName" contenteditable>
				</td>
			</tr>
			<tr>
				<th>Address</th>
				<td name="address" id="address" contenteditable>
				</td>
				<th>Town</th>
				<td name="town" id="town" contenteditable>
				</td>
			</tr>
		</table>
	</div>

	<div class="container">
		<h3>Invoice Details</h3>
		<table>
			<thead>
				<tr>
					<th>Code</th>
					<th>Article</th>
					<th>Units</th>
					<th>Price</th>
					<th>Subtotal</th>
					<th>Action</th>
				</tr>
			</thead>
			<tbody>
				<tr>
					<td class="code"></td>
					<td class="article"></td>
					<td class="units"></td>
					<td class="price"></td>
					<td class="subtotal"></td>
					<td class="action"></td>
				</tr>
			</tbody>
			<tfoot>
				<tr>
					<th colspan="4">Total articles</th>
					<th name="totalArticles" id="totalArticles">
					</th>
					<th>
						Add Line
					</th>
				</tr>
			</tfoot>
		</table>
	</div>

	<div class="container">
		<h3>Invoice Summary</h3>
		<table>
			<tr>
				<th>Discount</th>
				<td name="discount" id="discount">
				</td>
				<td class="no-border"></td>
				<th>Discount Import</th>
				<td name="discountImport" id="discountImport">
				</td>
				<td class="no-border"></td>
				<th>Taxable Base</th>
				<td name="taxableBase" id="taxableBase">
				</td>
			</tr>
			<tr>
				<td colspan="8"></td>
			</tr>
			<tr>
				<th>IVA</th>
				<td name="iva" id="iva">
				</td>
				<td class="no-border"></td>
				<th>IVA Import</th>
				<td name="ivaImport" id="ivaImport">
				</td>
				<td class="no-border"></td>
				<th>Total</th>
				<td name="total" id="total">
				</td>
			</tr>
		</table>
	</div>

	<div class="container">
		<button class="btn">Print</button>
		<input class="btn" type="submit" value="Save Invoice">
		<button class="btn">Cancel</button>
	</div>
</form>