<%@ tag language="java" pageEncoding="UTF-8"%>
<form method="post">
	<div class="container">
		<h3>Invoice & Client Informaiton</h3>
		<table>
			<tr>
				<th>Invoice date</th>
				<td>
                    <input type="date" name="invoiceDate" id="invoiceDate" style="border: none;">
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
				<td name="nif" id="nif">
				</td>
				<th>Name</th>
				<td colspan="3" name="clientName" id="clientName">
				</td>
			</tr>
			<tr>
				<th>Address</th>
				<td name="address" id="address">
				</td>
				<th>Town</th>
				<td name="town" id="town">
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
			<tbody id="invoice-lines">
				<tr>
				</tr>
			</tbody>
			<tfoot>
				<tr>
					<th class="text-right" colspan="4">Total articles</th>
					<td class="text-right euro" name="totalArticles" id="totalArticles"></td>
					<th id="add-line"><img src="./img/plus.svg" height="30px"></th>
				</tr>
			</tfoot>
		</table>
	</div>

	<div class="container">
		<h3>Invoice Summary</h3>
		<table>
			<tr>
				<th>Discount</th>
				<td class="text-right percentage" name="discount" id="discount">
				</td>
				<td class="no-border"></td>
				<th>Discount Import</th>
				<td class="text-right euro" name="discountImport" id="discountImport">
				</td>
				<td class="no-border"></td>
				<th>Taxable Base</th>
				<td class="text-right euro" name="taxableBase" id="taxableBase">
				</td>
            </tr>
			<tr>
				<th>IVA</th>
				<td class="text-right percentage" name="iva" id="iva"></td>
				<td class="no-border"></td>
				<th>IVA Import</th>
				<td class="text-right euro" name="ivaImport" id="ivaImport"></td>
				<td class="no-border"></td>
				<th>Total</th>
				<td class="text-right euro" name="total" id="total"></td>
			</tr>
		</table>
	</div>

	<div class="container">
		<button class="btn" id="print">Print</button>
		<input class="btn" id="send" type="submit" value="Save Invoice">
		<button class="btn" id="cancel">Cancel</button>
	</div>
</form>